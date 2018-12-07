# Automapstruct
[AutoValue](https://github.com/google/auto/blob/master/value/userguide/index.md) builder methods support for [MapStruct](http://mapstruct.org)


## Download
    repositories {
        ...
        maven { url 'https://raw.github.com/aradxxx/automapstruct/release/' }
    }
    
    implementation org.mapstruct:mapstruct:1.3.0.Beta2
    //instead mapStruct annotationProcessor (org.mapstruct:mapstruct-processor:1.3.0.Beta2})
    annotationProcessor "com.aradxxx:automapstruct:${latest_version}"
    
    
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

#### ContactMapperImpl.java (generated by MapConstruct)
````
public class ContactMapperImpl implements ContactMapper {

    @Override
    public ContactDto map(Contact contact) {
        if ( contact == null ) {
            return null;
        }

        Builder contactDto = ContactDto.builder();

        contactDto.walletAmount( contact.amount() );
        contactDto.name( contact.name() );
        contactDto.description( contact.description() );
        contactDto.age( contact.age() );

        contactDto.birth( contact.birth().getTime() );

        return contactDto.build();
    }

    @Override
    public Contact map(ContactDto contact) {
        if ( contact == null ) {
            return null;
        }

        com.automapstruct.automapper.Contact.Builder contact1 = Contact.builder();

        contact1.amount( contact.walletAmount() );
        contact1.description( contact.description() );
        contact1.name( contact.name() );
        contact1.age( contact.age() );

        contact1.birth( new java.util.Date(contact.birth()) );

        return contact1.build();
    }
}
````
#### Sample.java
```
...
ContactMapper mapper = ContactMapper.MAPPER;
Contact contact = mapper.map(contactDto);
...
```
