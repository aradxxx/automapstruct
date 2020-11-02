package automapstruct.sample;

import androidx.annotation.NonNull;

import com.google.auto.value.AutoValue;

import java.math.BigDecimal;
import java.util.Date;

@AutoValue
public abstract class Contact {
    @NonNull
    public abstract String name();

    @NonNull
    public abstract Date birth();

    @NonNull
    public abstract String description();

    public abstract int age();

    @NonNull
    public abstract BigDecimal amount();

    public abstract Builder toBuilder();

    public static Builder builder() {
        return new AutoValue_Contact.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder birth(Date birth);

        public abstract Builder description(String description);

        public abstract Builder name(String name);

        public abstract Builder age(int age);

        public abstract Builder amount(BigDecimal amount);

        public abstract Contact build();
    }
}
