// Styles
import '@mdi/font/css/materialdesignicons.css'
import 'vuetify/styles'

// Vuetify
import { createVuetify } from 'vuetify'

export default createVuetify(
  // https://vuetifyjs.com/en/introduction/why-vuetify/#feature-guides
)

export const staticPrimaryColor = '#008485'

export const thems = {
  light: {
    dark: false,
    colors: {
      'primary': staticPrimaryColor,
      'error': '#FF4C51',
      'warning': '#FF9F43',
    }
  }
}
