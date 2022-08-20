package tv.aysu.emilia.config;

import org.jetbrains.annotations.Nullable;

import java.io.File;

public class DefaultConfig extends Config{

    public DefaultConfig(File file, @Nullable String location) {
        super(file, location);
    }

    public DefaultConfig(File file) {
        super(file);
    }
}
