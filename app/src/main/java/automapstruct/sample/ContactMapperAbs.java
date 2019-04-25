package automapstruct.sample;

import android.support.annotation.NonNull;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public abstract class ContactMapperAbs {
    private static final String DATE_TO_MILLIS = "java(new java.util.Date(contact.birth()))";
    private static final String MILLIS_TO_DATE = "java(contact.birth().getTime())";

    @BeforeMapping
    void before(@NonNull Contact contact) {
    }

    @AfterMapping
    void after(@NonNull Contact contact) {
    }

    @NonNull
    @Mapping(target = "birth", expression = MILLIS_TO_DATE)
    @Mapping(source = "amount", target = "walletAmount")
    public abstract ContactDto map(@NonNull Contact contact);

    @NonNull
    @Mapping(target = "birth", expression = DATE_TO_MILLIS)
    @InheritInverseConfiguration
    public abstract Contact map(@NonNull ContactDto contact);
}
