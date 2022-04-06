package ru.job4j.design.lsp;

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
     * возвращает копию, чтобы нельзя было изменить напрямую
     */
    public List<Storage> getStorages() {
        return List.copyOf(storages);
    }
}
