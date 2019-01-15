package automapstruct.sample;

import android.support.annotation.NonNull;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ContactMapperTest {
    private static final String NAME = "Name";
    private static final String DESCRIPTION = "Description";
    private static final int AGE = 21;
    private static final BigDecimal AMOUNT = BigDecimal.TEN;
    private static final Date BIRTH = new Date(1355270400000L);

    private static final ContactMapperAbs MAPPER_ABS = new ContactMapperAbsImpl();

    private static final Contact contact = Contact.builder()
            .description(DESCRIPTION)
            .name(NAME)
            .age(AGE)
            .amount(AMOUNT)
            .birth(BIRTH)
            .build();

    private static final ContactDto contactDto = ContactDto.builder()
            .description(DESCRIPTION)
            .name(NAME)
            .age(AGE)
            .walletAmount(AMOUNT)
            .birth(BIRTH.getTime())
            .build();

    @Test
    public void toContactDto() {
        ContactDto mapped = ContactMapper.MAPPER.map(contact);
        assertContactDtoEquals(mapped);

    }

    @Test
    public void toContactDtoAbs() {
        ContactDto mappedAbs = MAPPER_ABS.map(contact);
        assertContactDtoEquals(mappedAbs);
    }

    @Test
    public void toContact() {
        Contact mapped = ContactMapper.MAPPER.map(contactDto);
        Contact mappedAbs = MAPPER_ABS.map(contactDto);
        assertContactEquals(mapped);
        assertContactEquals(mappedAbs);
    }

    @Test
    public void toContactAbs() {
        Contact mappedAbs = MAPPER_ABS.map(contactDto);
        assertContactEquals(mappedAbs);
    }

    @Test
    public void revertMapping() {
        ContactDto mapped = ContactMapper.MAPPER.map(contact);
        Contact reverted = ContactMapper.MAPPER.map(mapped);
        assertContactEquals(reverted);
    }

    @Test
    public void revertMappingAbs() {
        ContactDto mapped = MAPPER_ABS.map(contact);
        Contact reverted = MAPPER_ABS.map(mapped);
        assertContactEquals(reverted);
    }

    private void assertContactEquals(@NonNull Contact contact) {
        Assert.assertEquals(contact.age(), AGE);
        Assert.assertEquals(contact.birth().getTime(), BIRTH.getTime());
        Assert.assertEquals(contact.description(), DESCRIPTION);
        Assert.assertEquals(contact.name(), NAME);
        Assert.assertEquals(contact.amount(), AMOUNT);
    }

    private void assertContactDtoEquals(@NonNull ContactDto contact) {
        Assert.assertEquals(contact.age(), AGE);
        Assert.assertEquals(contact.birth(), BIRTH.getTime());
        Assert.assertEquals(contact.description(), DESCRIPTION);
        Assert.assertEquals(contact.name(), NAME);
        Assert.assertEquals(contact.walletAmount(), AMOUNT);
    }
}