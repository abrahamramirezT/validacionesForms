<!-- 
  La validacion de datos en este proyecto se llevo a cabo con vee-validate
  El proceso de intalación se encuentra en el siguiente enlace: 
  https://vee-validate.logaretm.com/v3/overview.html#installation
  
  Para mas referencias fuera de las documentadas en este codigo, puedes revisar: 
  La guia de vee validate en este enlace: 
  https://vee-validate.logaretm.com/v3/guide/basics.html#validation-provider 
-->

<template>
  <div style="margin-left: auto">
    <b-button v-b-modal.modal-1>Agregar Pokemón</b-button>
    <b-modal id="modal-1" title="Agregar Pokemón" hide-footer>
      <template #modal-header="{ close }">
        <h5>Agregar Pokemón</h5>
        <b-button
          size="sm"
          style="margin-left: auto"
          variant="outline-danger"
          @click="close()"
        >
          X
        </b-button>
      </template>
      <!-- 
        Para validar que todas las reglas de su formulario se cumplan antes de realizar el metodo de envio 
        Deben rodear la etiqueta <form></form> con <ValidationObserver v-slot="handleSubmit"></ValidatonObserver> 
        handelSubmit es un metodo por defecto de vee-validate, se debe dejar asi. 
        En el formulario deben pasar el metodo handleSubmit con su metodo de envio 
        como parametro, su metodo se ejecutara una vez todas las validaciones pasen.
      -->
      <ValidationObserver v-slot="{ handleSubmit }">
        <form id="form" @submit.prevent="handleSubmit(sendForm)">
          <!-- 
            Ejemplo basico de vee-validate:

            <ValidationProvider rules="even|required" v-slot="{ errors }">
            <input v-model="value" type="number" placeholder="type something" />
            <span>{{ errors[0] }}</span>
            </ValidationProvider> 
          -->

          <div class="fieldContainer">
            <div class="labelContainer">
              <label for="name"><b>Nombre del pokemón:</b></label>
            </div>
            <div class="inputContainer">
              <!-- 
                Todos los input deben ir rodeados por la etiqueta <ValidationProvider></ValidationProvider> 
                esta es la etiqueta brindada por vee-validate para colocar reglas de validacion a los inputs.
                En v-slot="{errors}" errors es un valor brindado por vee-validate, no hace falta declararlo 
                en los atributos data de vue, todos los errores por fallas a las reglas seran agregados de manera automatica
                a errors.
                rules="[reglas]" es el atributo para asignar reglas a los campos
              -->
              <ValidationProvider rules="required" v-slot="{ errors }">
                <!-- 
                  El atributo :class="{ invalid: errors[0] }" significa que la clase "invalid" sera agregada
                  al input en caso de que haya algun error encontrado.
                  La clase se encuentra declarada en las etiquetas style. 
                  Vee-validate tambien proporciona clases por defecto revisar: 
                  https://vee-validate.logaretm.com/v3/guide/state.html#css-classes
                -->
                <input
                  id="name"
                  v-model="name"
                  name="name"
                  type="text"
                  :class="{ invalid: errors[0] }"
                />
                <!-- Este span mostrara el primer error con el que se tope en caso de que alguna de las reglas no sea validada. -->
                <span class="errors">{{ errors[0] }}</span>
              </ValidationProvider>
            </div>
          </div>

          <!-- Text Area -->
          <div class="fieldContainer">
            <div class="labelContainer">
              <label for="description"><b>Descripción:</b></label>
            </div>
            <div class="inputContainer">
              <ValidationProvider
                rules="required|min:50|descriptionEscape"
                v-slot="{ errors }"
              >
                <textarea
                  id="description"
                  v-model="description"
                  name="description"
                  rows="3"
                  :class="{ invalid: errors[0] }"
                ></textarea>
                <span class="errors">{{ errors[0] }}</span>
              </ValidationProvider>
            </div>
          </div>

          <!-- Validacion de email-->
          <div class="fieldContainer">
            <div class="labelContainer">
              <label for="email"><b>Email de tu pokemón:</b></label>
            </div>
            <div class="inputContainer">
              <ValidationProvider
                rules="required|email_format"
                v-slot="{ errors }"
              >
                <input
                  id="email"
                  v-model="email"
                  name="email"
                  rows="3"
                  :class="{ invalid: errors[0] }"
                  type="email"
                />
                <span class="errors">{{ errors[0] }}</span>
              </ValidationProvider>
            </div>
          </div>

          <!-- Validacion del color -->
          <div class="fieldContainer">
            <div class="labelContainer">
              <label for="color"><b>Color de tu pokemón:</b></label>
            </div>
            <div class="inputContainer">
              <ValidationProvider rules="required|colorHex" v-slot="{ errors }">
                <input
                  style="height: 35px"
                  type="color"
                  name="color"
                  id="color"
                  v-model="color"
                  @input="updateColor"
                />
                <span class="errors">{{ errors[0] }}</span>
              </ValidationProvider>
            </div>
          </div>

          <!-- radio buttons-->
          <div class="fieldContainer">
            <div class="labelContainer">
              <label><b>Generación:</b></label>
            </div>
            <div class="inputContainer">
              <ValidationProvider rules="required" v-slot="{ errors }">
                <div class="select-container">
                  <label for="tipo1">Primera Generacion</label>
                  <input
                    type="radio"
                    id="gen1"
                    value="Primera Generacion"
                    v-model="selectedTipo"
                  />
                </div>
                <div class="select-container">
                  <label for="tipo2">Segunda Generacion</label>
                  <input
                    type="radio"
                    id="gen2"
                    value="Segunda Generacion"
                    v-model="selectedTipo"
                  />
                </div>
                <div class="select-container">
                  <label for="tipo3">Tercera Generacion</label>
                  <input
                    type="radio"
                    id="gen3"
                    value="Tercera Generacion"
                    v-model="selectedTipo"
                  />
                </div>
                <span class="errors">{{ errors[0] }}</span>
              </ValidationProvider>
            </div>
          </div>

          <!--  Check  Box-->
          <div class="fieldContainer">
            <div class="labelContainer">
              <label><b>Habilidades:</b></label>
            </div>
            <div class="inputContainer">
              <ValidationProvider rules="required" v-slot="{ errors }">
                <div class="select-container">
                  <label for="tipo1">Absorción agua</label>
                  <input
                    type="checkbox"
                    id="tipo1"
                    value="Absorción agua"
                    v-model="selectedValues"
                  />
                </div>
                <div class="select-container">
                  <label for="tipo2">Absorción fuego</label>
                  <input
                    type="checkbox"
                    id="tipo2"
                    value="Absorción fuego"
                    v-model="selectedValues"
                  />
                </div>
                <div class="select-container">
                  <label for="tipo3">Absorción electricidad</label>
                  <input
                    type="checkbox"
                    id="tipo3"
                    value="Absorción electricidad"
                    v-model="selectedValues"
                  />
                </div>
                <span class="errors">{{ errors[0] }}</span>
              </ValidationProvider>
            </div>
          </div>

          <!-- Rangos-->
          <div class="fieldContainer">
            <div class="labelContainer">
              <label for="habilidades"><b>Fuerza del pokemón:</b></label>
            </div>
            <div class="inputContainer">
              <ValidationProvider
                rules="required|range200to3000"
                v-slot="{ errors }"
              >
                <div>
                  <input
                    type="range"
                    id="habilidades"
                    min="0"
                    max="5000"
                    v-model="fuerza"
                  />
                  <output>{{ fuerza }}</output>
                  <br />
                  <span class="errors">{{ errors[0] }}</span>
                </div>
              </ValidationProvider>
            </div>
          </div>

          <!-- Password -->
          <div class="fieldContainer">
            <div class="labelContainer">
              <label for="password"><b>Contraseña:</b></label>
            </div>
            <div class="inputContainer">
              <ValidationProvider
                rules="required|passwordValidation|password:@confirm"
                v-slot="{ errors }"
              >
                <div>
                  <input
                    type="password"
                    :class="{ invalid: errors[0] }"
                    id="paswword"
                    v-model="password"
                  />
                  <span class="errors">{{ errors[0] }}</span>
                </div>
              </ValidationProvider>
            </div>
          </div>

          <div class="fieldContainer">
            <div class="labelContainer">
              <label for="password"><b>Repetir Contraseña:</b></label>
            </div>
            <div class="inputContainer">
              <ValidationProvider
                rules="required|passwordValidation"
                v-slot="{ errors }"
                name="confirm"
              >
                <div>
                  <input
                    type="password"
                    :class="{ invalid: errors[0] }"
                    id="paswwordRepeat"
                    v-model="confirmation"
                  />
                  <span class="errors">{{ errors[0] }}</span>
                </div>
              </ValidationProvider>
            </div>
          </div>

          <!-- números -->
          <div class="fieldContainer">
            <div class="labelContainer">
              <label for="pokemonWeight"><b>Peso del pokemón:</b></label>
            </div>
            <div class="inputContainer">
              <ValidationProvider
                rules="required|weightPositive"
                v-slot="{ errors }"
              >
                <input
                  id="pokemonWeight"
                  v-model="pokemonWeight"
                  name="pokemonWeight"
                  step="0.1"
                  type="number"
                  :class="{ invalid: errors[0] }"
                />
                <span class="errors">{{ errors[0] }}</span>
              </ValidationProvider>
            </div>
          </div>

          <!-- números -->
          <div class="fieldContainer">
            <div class="labelContainer">
              <label for="pokemonSize"><b>Tamaño del pokemón:</b></label>
            </div>
            <div class="inputContainer">
              <ValidationProvider
                rules="required|sizePositive"
                v-slot="{ errors }"
              >
                <input
                  id="pokemonSize"
                  v-model="pokemonSize"
                  name="pokemonSize"
                  step="0.1"
                  type="number"
                  :class="{ invalid: errors[0] }"
                />
                <span class="errors">{{ errors[0] }}</span>
              </ValidationProvider>
            </div>
          </div>

          <div class="fieldContainer">
            <div class="labelContainer">
              <label for="pokemonWeight"><b>Imagen del pokemón:</b></label>
            </div>
            <div class="inputContainer">
              <ValidationProvider
                rules="required|ext:png"
                v-slot="{ errors, validate }"
              >
                <input
                  type="file"
                  :class="{ invalid: errors[0] }"
                  @change="validate"
                  @input="handleImageUpload"
                />
                <span class="errors">{{ errors[0] }}</span>
              </ValidationProvider>
            </div>
          </div>

          <div class="fieldContainer">
            <div class="labelContainer">
              <label for="type"><b>Tipo:</b></label>
            </div>
            <div class="inputContainer">
              <ValidationProvider rules="required" v-slot="{ errors }">
                <select
                  id="type"
                  v-model="selectedType"
                  name="type"
                  :class="{ invalid: errors[0] }"
                >
                  <option
                    v-for="type in types"
                    :key="type.typeId"
                    :value="type.typeId"
                  >
                    {{ type.name }}
                  </option>
                </select>
                <span class="errors">{{ errors[0] }}</span>
              </ValidationProvider>
            </div>
          </div>

          <div class="buttonsContainer">
            <button type="submit" id="botonEnviar">Enviar</button>
            <button type="button" @click="closeModal" id="botonCancelar">
              Cancelar
            </button>
          </div>
        </form>
      </ValidationObserver>
    </b-modal>
  </div>
</template>

<script>
import axios from "axios";
import { extend } from "vee-validate";
//Importaciones de las reglas por defecto de vee-validate
//Deben importarse de manera manual todas las reglas, revisar:
//https://vee-validate.logaretm.com/v3/guide/rules.html#rules
import { required, min, ext } from "vee-validate/dist/rules";

//Reglas con un mensaje de error personalizado, en este caso se cambio el mensaje a español
extend("required", {
  ...required,
  message: "Este campo es requerido",
});

//Esta regla valida la extension de la imagen
extend("ext", {
  ...ext,
  message: "La imagen debe ser un png",
});

//Esta regla incluye el parametro lenght para incluirlo en el mensaje de error
extend("min", {
  ...min,
  params: ["length"],
  message: "El valor debe tener un minimo de {length} caracteres",
});

//Esta regla es personalizada, vee-validate te permite crear cualquier regla que desees de manera manual
//en caso de que no te sirva ninguna de las reglas que tiene por defecto.
//El nombre de esta regla es sizePositive y valida que el valor de pokemonSize declarado en data sea positivo
//cuando haya cambios por el v-model que esta en el input
//Finalmente el mensaje de error en español en caso de que la regla no se aplique.
extend("sizePositive", (pokemonSize) => {
  if (pokemonSize > 0) {
    return true;
  }
  return "El tamaño del pokemon debe ser positivo";
});

extend("descriptionEscape", (description) => {
  if (description) {
    const regex = /^[a-zA-Z.,\s]*$/;
    if (regex.test(description)) {
      return true;
    }
    return "No se puede usar esa descripción, solo debe contener letras, puntos y comas";
  }
});

//Regla que valida que el campo del email sea de tipo email
extend("email_format", {
  validate: (value) => {
    // Utiliza una expresión regular para validar el formato del correo electrónico
    return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(value);
  },
  message: "El campo debe ser una dirección de correo electrónico válida",
});

//Misma logica que sizePositive aplicada para otro valor de data
extend("weightPositive", (pokemonWeight) => {
  if (pokemonWeight > 0) {
    return true;
  }
  return "El peso del pokemon debe ser positivo";
});

// Regla que valida que el campo del color debe ser un valor hexadecimal
extend("colorHex", {
  validate: (value) => /^#[0-9A-Fa-f]{6}$/.test(value),
  message:
    'El campo color del pokemon  debe ser un color hexadecimal válido en formato "#rrggbb".',
});

// Regla que valida que el valor de la fuerza sea mayor a 200 y menor que 3000
extend("range200to3000", {
  validate(value) {
    const isValid = value >= 200 && value <= 3000;
    if (!isValid) {
      console.log("El valor debe estar entre 200 y 3000");
    }
    return isValid;
  },
  message: "El valor debe estar entre 200 y 3000",
});

//Regla que valida la contraseña
extend("passwordValidation", {
  validate(value) {
    // Al menos una letra mayúscula
    const uppercaseRegex = /[A-Z]/;
    // Al menos un número
    const numberRegex = /\d/;
    // Sin caracteres especiales
    const specialCharRegex = /^[a-zA-Z0-9]*$/;

    return (
      uppercaseRegex.test(value) &&
      numberRegex.test(value) &&
      specialCharRegex.test(value)
    );
  },
  message:
    "La contraseña debe contener al menos una letra mayúscula, un número y no puede contener caracteres especiales.",
});

extend("password", {
  params: ["target"],
  validate(value, { target }) {
    return value === target;
  },
  message: "La contraseña repetida no concuerda con la contraseña ingresada",
});

export default {
  name: "FormModal",
  data() {
    return {
      name: null,
      description: null,
      selectedType: null,
      pokemonSize: null,
      pokemonWeight: null,
      imageBase64: null,
      imageFile: null,
      email: null,
      activarRadioButtons: false,
      selectedTipo: null,
      fuerza: 250,
      color: "#rrggbb",
      selectedValues: [],
      fuerza: 0,
      password: null,
      types: [],
      confirmation: null,
    };
  },
  methods: {
    sendForm() {
      const selectedValuesString = this.selectedValues.join(",");
      axios
        .post("http://localhost:8080/api/pokemons", {
          name: this.name,
          description: this.description,
          size: this.pokemonSize,
          weight: this.pokemonWeight,
          email: this.email,
          color: this.color,
          type: {
            typeId: this.selectedType,
          },
          imageBase64: this.imageBase64,
          selectedTipo: this.selectedTipo,
          selectedValues: selectedValuesString,
          fuerza: this.fuerza,
          password: this.password,
        })
        .then((response) => {
          this.name = null;
          this.description = null;
          this.pokemonSize = null;
          this.pokemonWeight = null;
          this.selectedType = null;
          this.imageBase64 = null;
          this.email = null;
          this.color = null;
          this.selectedTipo = null;
          this.selectedValues = null;
          this.fuerza = null;
          this.password = null;
          //   this.errors = [];
          this.$emit("registroExitoso");
          this.$swal({
            title: "Agregada",
            text: "El pokemon ha sido agregada con exito",
            icon: "success",
          });
          this.$root.$emit("bv::hide::modal", "modal-1");
        })
        .catch((error) => {
          if (
            error.response &&
            error.response.data &&
            error.response.data.data
          ) {
            const errors = error.response.data.data;
            console.log(errors);
            let errorMessage =
              "Ha ocurrido un error con la validacion de valores en spring:";
            errors.forEach((error) => {
              errorMessage += "\n- " + error;
            });
            this.$swal({
              title: "Error",
              text: errorMessage,
              icon: "error",
            });
          } else {
            console.error(error);
          }
        });
    },
    closeModal() {
      this.$root.$emit("bv::hide::modal", "modal-1");
      this.name = null;
      this.description = null;
      this.selectedType = null;
      this.pokemonSize = null;
      this.pokemonWeight = null;
      this.email = null;
      this.color = null;
      this.selectedTipo = null;
      this.selectedValues = null;
      this.fuerza = null;
      this.password = null;
    },
    handleImageUpload(event) {
      this.imageFile = event.target.files[0];
      const reader = new FileReader();
      reader.readAsDataURL(this.imageFile);
      reader.onload = () => {
        let base64Image = reader.result;
        // Obtener el tipo de imagen desde el nombre del archivo o del tipo MIME del archivo
        let imageType = "image/png"; // Puedes cambiar esto según el tipo de archivo que esperas
        // Agregar la cabecera correspondiente al tipo de imagen
        this.imageBase64 = `data:${imageType};base64,${
          base64Image.split(",")[1]
        }`;
      };
    },
    updateFuerza(event) {
      this.fuerza = parseInt(event.target.value);
    },
    updateColor() {
      // Validar el formato del color
      if (!/^#[0-9A-Fa-f]{6}$/i.test(this.color)) {
        // Mostrar un mensaje de error o tomar alguna otra acción
        console.error("El color seleccionado no es válido.");
      }
    },
    handleCheckboxChange(newValue) {
      if (!newValue) {
        this.selectedTipo = null; // Establece el valor del radio button como null cuando se desactiva el checkbox
      }
    },
  },
  mounted() {
    axios
      .get("http://localhost:8080/api/types")
      .then((response) => {
        this.types = response.data;
      })
      .catch((e) => {
        console.error("Error en la peticion: ", e);
      });
  },
};
</script>

<style>
.buttonsContainer {
  width: 100%;
  display: flex;
  justify-content: space-evenly;
}

.buttonsContainer button {
  width: 30%;
  padding: 10px 20px;
  border-radius: 10px;
  border: none;
  margin-bottom: 10px;
  margin-top: 10px;
  transition: width 0.3s;
}

.buttonsContainer button:hover {
  width: 35%;
}

#botonEnviar {
  background-color: rgb(51, 139, 240);
  color: white;
}

#botonCancelar {
  background-color: rgb(240, 51, 51);
  color: white;
}

#form {
  width: 100%;
  padding: 10px;
}

.fieldContainer {
  width: 100%;
  margin-bottom: 20px;
}

.labelContainer {
  margin-bottom: 10px;
}

.inputContainer {
  width: 100%;
}

.inputContainer input {
  padding: 10px;
  width: 100%;
  border: 2px solid #ccc;
  border-radius: 10px;
  background-color: #f9f9f9;
  color: #333;
  outline: none;
}

.inputContainer input:focus {
  border-color: #2b2b2b;
}
.inputContainer textarea {
  padding: 10px;
  width: 100%;
  border: 2px solid #ccc;
  border-radius: 10px;
  background-color: #f9f9f9;
  color: #333;
  outline: none;
}

.inputContainer textarea:focus {
  border-color: #2b2b2b;
}

.inputContainer select {
  padding: 10px;
  width: 100%;
  border: 2px solid #ccc;
  border-radius: 10px;
  background-color: #f9f9f9;
  color: #333;
  outline: none;
}

.inputContainer select:focus {
  border-color: #2b2b2b;
}

.invalid {
  border-color: red !important;
  background-color: rgb(255, 255, 255) !important;
}

.errors {
  color: red;
}

.select-container {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

.select-container label {
  width: 170px;
}

.select-container input {
  width: 30%;
}
</style>
