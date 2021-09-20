package nordside.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "partners")
public class Partner extends AbstractNamedEntity{
    @Column
    @NotBlank
    private String name;

    @Column
    private String telephone;

    @Column
    private String site;

    @Column
    private String address;
}
