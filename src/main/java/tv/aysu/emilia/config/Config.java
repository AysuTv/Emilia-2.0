package tv.aysu.emilia.config;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.simpleyaml.configuration.file.YamlFile;
import org.simpleyaml.exceptions.InvalidConfigurationException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;

public abstract class Config {

    @Getter
    private final File file;
    private final String location;

    private final ClassLoader loader = this.getClass().getClassLoader();

    @Getter
    private YamlFile configuration;

    @Getter
    private final String name;

    public Config(File file, @Nullable String location) {
        this.file = file;
        this.location = location;
        this.name = this.file.getName();
        this.load();
    }

    public Config(File file) {
        this.file = file;
        this.location = null;
        this.name = this.file.getName();

        this.load();
    }

    public void afterLoad() {}

    public boolean contains(@NotNull String path) {
        return this.configuration.contains(path);
    }

    @NotNull
    public final String getString(@NotNull String path) {
        return this.contains(path) ? this.configuration.getString(path) : "";
    }

    @NotNull
    public final List<String> getStringList(@NotNull String path) {
        return this.contains(path) ? this.configuration.getStringList(path) : Collections.emptyList();
    }

    public final int getInt(@NotNull String path) {
        return this.contains(path) ? this.configuration.getInt(path) : 0;
    }

    public final boolean getBoolean(@NotNull String path) {
        return this.contains(path) && this.configuration.getBoolean(path);
    }

    @NotNull
    public final List<?> getList(@NotNull String path) {
        return this.contains(path) ? this.configuration.getList(path) : Collections.emptyList();
    }

    public final void save() {
        try {
            configuration.save(file);
        } catch (IOException e) {
            //ignore
        }
    }

    private void load() {

        if(!file.exists()) {

            //file does not exist
            file.getParentFile().mkdirs();

            if (location != null) {
                try (InputStream stream = loader.getResourceAsStream(location)) {

                    if (stream == null) {
                        return;
                    }

                    Files.copy(stream, file.toPath());
                    System.out.println("Copied file");
                    System.out.println("Copied file");
                    System.out.println("Copied file");
                } catch (IOException e) {
                    //ignore
                }
            } else {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    //ignore
                }
            }
        }

        this.configuration = new YamlFile(file);
        System.out.println("Safed file");
        System.out.println("Safed file");
        System.out.println(configuration);

        try {
            this.configuration.loadWithComments();
        } catch (IOException | InvalidConfigurationException e) {
            //ignore
        }

        System.out.println("Loaded file");

            /*
            Check for new values and update them
             */

        if(location != null) {
            YamlFile tempFile = new YamlFile();
            try {
                tempFile.load(this.loader.getResourceAsStream(location));
            } catch (IOException | InvalidConfigurationException e) {
                //ignore
            }

            //remove the values which shouldn't be there anymore
            for(String key : configuration.getKeys(true)) {
                if(!tempFile.contains(key))
                    configuration.set(key, null);
            }

            //add the new values
            for(String key : tempFile.getKeys(true)) {
                if(!configuration.contains(key))
                    configuration.set(key, tempFile.get(key));
            }

            System.out.println("Updated file");
        }

        System.out.println("Finished loading file");
        this.save();
        this.afterLoad();
    }

}
