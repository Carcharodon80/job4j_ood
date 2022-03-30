package ru.job4j.design.lsp;

import java.util.List;

public class ControlQuality {
    private List<Storage> storages;

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

    public List<Storage> getStorages() {
        return storages;
    }
}
