package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private final List<Storage> storages;

    public ControlQuality(List<Storage> storages) {
        this.storages = storages;
    }

    public void distribute(Food food) {
        for (Storage storage : storages) {
            if (storage.check(food)) {
                storage.add(food);
            }
        }
    }

    /**
     * Перераспределяем еду в хранилищах, в зависимости от текущей даты:
     * берем список хранилищ, получаем список еды, очищаем хранилица, распределяем еду
     */
    public void resort() {
        List<Food> allFoods = new ArrayList<>();
        for (Storage storage : getStorages()) {
            allFoods.addAll(storage.getFoodList());
            clearStorage(storage);
        }
        for (Food food : allFoods) {
            distribute(food);
        }
    }

    /**
     * очищаем список еды в хранилище
     */
    private void clearStorage(Storage storage) {
        storage.removeAllFoods();
    }

    /**
     * возвращает копию, чтобы нельзя было изменить напрямую
     */
    public List<Storage> getStorages() {
        return List.copyOf(storages);
    }
}
