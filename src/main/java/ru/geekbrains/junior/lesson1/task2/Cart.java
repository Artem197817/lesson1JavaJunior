package ru.geekbrains.junior.lesson1.task2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Корзина
 *
 * @param <T> Еда
 */
public class Cart<T extends Food> {

    //region Поля

    /**
     * Товары в магазине
     */
    private final ArrayList<T> foodstuffs;
    private final UMarket market;
    private final Class<T> clazz;

    //endregion

    //region Конструкторы

    /**
     * Создание нового экземпляра корзины
     *
     * @param market принадлежность к магазину
     */
    public Cart(Class<T> clazz, UMarket market) {
        this.clazz = clazz;
        this.market = market;
        foodstuffs = new ArrayList<>();
    }

    public void cardBalancing() {
        boolean proteins;
        boolean fats;
        boolean carbohydrates;

        proteins = foodstuffs.stream().anyMatch(Food::getProteins);
        fats = foodstuffs.stream().anyMatch(Food::getFats);
        carbohydrates = foodstuffs.stream().anyMatch(Food::getCarbohydrates);

        if (proteins && fats && carbohydrates) {
            System.out.println("Корзина уже сбалансирована по БЖУ.");
            return;
        }
        Collection<T> listThings = market.getThings(clazz);
        if (!proteins) {
            var thingProtein = listThings.stream().filter(Food::getProteins).findFirst().orElse(null);
            if (thingProtein != null) {
                proteins = true;
                foodstuffs.add(thingProtein);
            }
        }
        if (!fats) {
            var thingFat = listThings.stream().filter(Food::getFats).findFirst().orElse(null);
            if (thingFat != null) {
                fats = true;
                foodstuffs.add(thingFat);
            }
        }
        if (!carbohydrates) {
            var thingCarbohydrates = listThings.stream().filter(Food::getCarbohydrates).findFirst().orElse(null);
            if (thingCarbohydrates != null) {
                carbohydrates = true;
                foodstuffs.add(thingCarbohydrates);
            }
        }
        if (proteins && fats && carbohydrates)
            System.out.println("Корзина сбалансирована по БЖУ.");
        else
            System.out.println("Невозможно сбалансировать корзину по БЖУ.");

    }

    //endregion

    public Collection<T> getFoodstuffs() {
        return foodstuffs;
    }


    public void printFoodstuffs() {
        /*int index = 1;
        for (var food : foodstuffs)
            System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n", index++, food.getName(), food.getProteins() ? "Да" : "Нет",
                    food.getFats() ? "Да" : "Нет", food.getCarbohydrates() ? "Да" : "Нет");*/


        AtomicInteger index = new AtomicInteger(1);
        foodstuffs.forEach(food -> System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n",
                index.getAndIncrement(), food.getName(),
                food.getProteins() ? "Да" : "Нет",
                food.getFats() ? "Да" : "Нет",
                food.getCarbohydrates() ? "Да" : "Нет"));
    }
}
