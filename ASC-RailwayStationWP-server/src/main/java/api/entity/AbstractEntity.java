package api.entity;

import javax.persistence.*;

/**
 * Created by nolesuk on 21-Feb-17.
 */

@MappedSuperclass
public class AbstractEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public AbstractEntity() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AbstractEntity that = (AbstractEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public boolean isPersistence() {
        return id != null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        if (this.id == null) {
            this.id = id;
        } else {
            throw new IllegalStateException("can't change entity's id! \n id = " + id);
        }
    }
}
