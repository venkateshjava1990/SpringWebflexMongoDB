package spring.webflex.SpringWebflexMongoDB.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
@Data
public class AccountEvent implements Serializable {

    private String id;
    private String owner;
    private Double value;
   private Date date;
    public AccountEvent(String id,String owner,Double value,Date date){
      this.id=id;
      this.owner=owner;
      this.value=value;
      this.date=date;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public AccountEvent() {
    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "AccountEvent{" +
                "id='" + id + '\'' +
                ", owner='" + owner + '\'' +
                ", value=" + value +
                ", date=" + date +
                '}';
    }
}
