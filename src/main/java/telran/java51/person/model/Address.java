package telran.java51.person.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address implements Serializable{
	private static final long serialVersionUID = -1008687093519442036L;
	String city;
	String street;
	String building;

}
