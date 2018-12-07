# Automapstruct
AutoValue build methods support for [MapStruct](http://mapstruct.org)


## Download
    repositories {
        maven { url 'https://raw.github.com/aradxxx/automapstruct/release/' }
    }
    
    
### Usage
#### Contact.java
```
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
```   
    
    
#### ContactDto.java
```
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

    public abstract Builder toBuilder();

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
```


#### ContactMapper.java
```
@Mapper
public interface ContactMapper {
    String DATE_TO_MILLIS = "java(new java.util.Date(contact.birth()))";
    String MILLIS_TO_DATE = "java(contact.birth().getTime())";

    ContactMapper MAPPER = Mappers.getMapper(ContactMapper.class);

    @NonNull
    @Mapping(target = "birth", expression = MILLIS_TO_DATE)
    @Mapping(source = "amount", target = "walletAmount")
    ContactDto map(@NonNull Contact contact);

    @NonNull
    @Mapping(target = "birth", expression = DATE_TO_MILLIS)
    @InheritInverseConfiguration
    Contact map(@NonNull ContactDto contact);
}
```
