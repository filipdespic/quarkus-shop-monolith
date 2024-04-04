package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.abstr.AbstractEntity;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "reviews")
public class Review extends AbstractEntity {


}
