package appbuilder.backapp.core.data;

import java.util.ArrayList;
import java.util.List;

public class FormDTO {
    private String id;
    private String label;
    private String nome;

    private List<FieldDTO>fields = new ArrayList<>();


    public List<FieldDTO> getFields() {
        return fields;
    }

    public void setFields(List<FieldDTO> fields) {
        this.fields = fields;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
