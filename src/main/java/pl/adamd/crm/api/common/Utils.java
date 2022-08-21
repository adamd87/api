package pl.adamd.crm.api.common;

import java.util.Objects;
import java.util.function.Consumer;

public class Utils {
    public static <V> void setIfNotNull(V value, Consumer<V> setter) {
        if (Objects.nonNull(value)) {
            setter.accept(value);
        }
    }
}
