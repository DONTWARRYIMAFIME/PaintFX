package org.paintFX.core;

import javafx.scene.image.Image;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public interface IService {
    String getToolName();
    Image getIcon();
    ShapeFactory createFactory();

    static List<IService> getServices(ModuleLayer layer) {
        return ServiceLoader
                .load(layer, IService.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
    }
}
