package automapstruct.sample;

import androidx.annotation.NonNull;

import com.google.auto.value.AutoValue;

import java.math.BigDecimal;

@AutoValue
public abstract class ContactDto {
    @NonNull
    public abstract String name();

    @NonNull
    public abstract long birth();

    @NonNull
    public abstract String description();

    public abstract int age();

    @NonNull
    public abstract BigDecimal walletAmount();

    public static Builder builder() {
        return new AutoValue_ContactDto.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder name(String name);

        public abstract Builder birth(long birth);

        public abstract Builder description(String description);

        public abstract Builder age(int age);

        public abstract Builder walletAmount(BigDecimal walletAmount);

        public abstract ContactDto build();
    }
}
