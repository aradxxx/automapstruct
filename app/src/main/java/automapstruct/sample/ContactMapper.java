package automapstruct.sample;

import android.support.annotation.NonNull;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

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
