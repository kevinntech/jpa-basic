package jpql;

import javax.persistence.*;
import java.beans.PropertyEditorSupport;

@Entity
@Table(name = "ORDERS") // 데이터베이스에서 ORDER가 예약어인 경우가 있음
public class Order {

    @Id @GeneratedValue
    private Long id;
    private int orderAmount;

    @Embedded
    private Address address;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

}
