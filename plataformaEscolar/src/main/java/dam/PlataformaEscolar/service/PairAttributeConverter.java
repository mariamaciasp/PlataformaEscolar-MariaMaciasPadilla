package dam.PlataformaEscolar.service;

import org.springframework.data.util.Pair;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class PairAttributeConverter implements AttributeConverter<Pair<String, String>, String> {


    @Override
    public String convertToDatabaseColumn(Pair<String, String> attribute) {

        if (attribute == null)
            return null;

        String result = "";

        if (attribute.getFirst() != null)
            result += attribute.getFirst() + ",";

        if (attribute.getSecond() != null) {
            result += attribute.getSecond();
        }

        return result;


    }

    @Override
    public Pair<String, String> convertToEntityAttribute(String dbData) {

        /*
            dbData == null -> No hay datos
            sin coma, un dato -> first = null, second = dato
            un dato con coma -> first = dato, second = null
            dos datos, con coma -> first = dato1, second = dato2
         */

        if (dbData == null)
            return null;

        if (dbData.isBlank())
            return null;

        if (!dbData.contains(",")) {
            return Pair.of(null, dbData.trim());
        } else {
            String[] partes = dbData.split(",");
            String first = partes[0].trim();
            String second = (partes[1].trim()).isBlank() ? null : partes[1].trim();
            return Pair.of(first, second);
        }

    }
}