import java.util.HashMap;
import java.util.Map;

public class Merchant implements Seller {

    // Инвентарь предметов (изначально пустой)
    private Map<Goods, Integer> inventory = new HashMap<>();

    // Деньги, доступные для транзакций
    private int gold; // Первоначальные деньги

    // Метод продажи предметов
    @Override
    public String sell(Goods goods) {
        if (inventory.containsKey(goods) && inventory.get(goods) > 0) {
            // Товар доступен
            inventory.put(goods, inventory.get(goods) - 1); // Уменьшаем запас
            return goods.toString(); // Возвращаемое имя элемента
        } else {
            // Товар закончился на складе или недоступен
            return "Извини, но " + goods + " у меня нет.";
        }
    }

    // Перечисление для элементов (при необходимости можно добавить)
    public enum Goods {
        POTION,
        SWORD,
        ARMOR
    }

    // Способ проверить, достаточно ли у игрока денег
    public boolean hasEnoughMoney(Goods goods) {
        switch (goods) {
            case POTION:
                return gold >= 10; // цена 10
            case SWORD:
                return gold >= 50; // цена 50
            case ARMOR:
                return gold >= 100; // цена 100
            default:
                return false; // Неверный элемент
        }
    }

    // Способ покупки предметов (вызывается после победы над врагом)
    public void buyItem(Goods goods) {
        if (hasEnoughMoney(goods)) {
            inventory.put(goods, inventory.getOrDefault(goods, 0) + 1); // Добавьте или увеличьте запас
            gold -= getGoodsPrice(goods); // Вычетание дененг
            System.out.println("Вы купили " + goods + ".");
        } else {
            System.out.println("У вас недостаточно денег, чтобы купить " + goods + ".");
        }
    }

    // Метод получения цены товара
    private int getGoodsPrice(Goods goods) {
        switch (goods) {
            case POTION:
                return 10;
            case SWORD:
                return 50;
            case ARMOR:
                return 100;
            default:
                return 0; // Неверный элемент
        }
    }

    // Метод добавления предметов в инвентарь торговца (изначально пустой)
    public void addItems(Goods goods, int quantity) {
        inventory.put(goods, quantity);
    }
}
