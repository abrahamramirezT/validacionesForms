package formularioValidacion.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.List;


/*
   Las validaciones de bean en Spring Boot se realizan antes de que los datos se envíen a la base de datos.
   La idea detrás de las validaciones de beans es asegurarse de que los datos ingresados en tu aplicación
   cumplan con ciertos criterios y restricciones antes de que se realice cualquier operación persistente,
   como guardar o actualizar en la base de datos. Esto se hace para garantizar la integridad y
   la calidad de los datos almacenados en la base de datos.
*/

/*
   La dependencia utilzada se encuentra en el archivo pom.xml
*/

@Entity
@Table(name = "pokemons")
public class Pokemon {

    
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "pokemon_id")
    private Long pokemonId;
    @NotNull(message = "El nombre no debe ser nulo")
    @NotBlank(message = "El nombre no debe ir vacío")
    @Pattern(regexp = "^[a-zA-Z.,\\s]*$", message = "El campo solo puede contener letras, puntos y comas")
    private String name;

    @NotNull(message = "El peso no debe ser nulo")
    @Positive(message = "El peso debe ser un número positivo")
    private Float weight;

    @Lob
    @Column(name = "image_base64", columnDefinition = "LONGTEXT")
    @Pattern(regexp = "^data:image\\/png;base64,[A-Za-z0-9+/]+={0,2}$", message = "La imagen debe ser en formato PNG")
    private String imageBase64;

    @NotNull(message = "El tamaño no debe ser nulo")
    @Positive(message = "El tamaño debe ser un número positivo")
    private Float size;

    @NotNull(message = "La descripcion no debe ser nula")
    @NotBlank(message = "La descripción no debe estar en blanco")
    @Size(min = 50, message = "La descripción debe tener al menos 50 caracteres")
    @Pattern(regexp = "^[a-zA-Z.,\\s]*$", message = "El campo solo puede contener letras, puntos y comas")
    private String description;


    @ManyToOne
    @JoinColumn(name = "type_id")
    @Valid // Habilitar la cascada de validación, va a traer consigo las validaciones de la clase Type
    private Type type;


    private String selectedTipo;

    @Email(message = "Debe ser una dirección de correo electrónico válida")
    private String email;



    @Pattern(regexp = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$", message = "El color debe ser un valor hexadecimal válido")
    private String color;




    @NotBlank(message = "Los valores seleccionados no pueden estar vacíos")
    private String selectedValues;



    @Min(value = 200, message = "La fuerza no puede ser menor que 200")
    @Max(value = 3000, message = "La fuerza no puede ser mayor que 3000")
    private int fuerza;

    @NotNull(message = "La contraseña no debe ser nula")
    @NotBlank(message = "La contraseña no debe estar en blanco")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]*$", message = "La contraseña debe contener al menos una letra mayúscula y un número, y no puede contener caracteres especiales")
    private String password;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public String getSelectedValues() {
        return selectedValues;
    }

    public void setSelectedValues(String selectedValues) {
        this.selectedValues = selectedValues;
    }
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSelectedTipo() {
        return selectedTipo;
    }

    public void setSelectedTipo(String selectedTipo) {
        this.selectedTipo = selectedTipo;
    }



    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public Long getPokemonId() {
        return pokemonId;
    }

    public void setPokemonId(Long pokemonId) {
        this.pokemonId = pokemonId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getSize() {
        return size;
    }

    public void setSize(Float size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
