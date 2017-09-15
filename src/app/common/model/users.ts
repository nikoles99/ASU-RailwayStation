export class User {
  login: string;
  password: string;
  confirmPassword: string;
  name: string;
  lastName: string;
  phone: string;
  email: string;
  loginError: string;
  passwordError: string;
  confirmPasswordError: string;
  firstNameError: string;
  lastNameError: string;
  emailError: string;

  public clearErrors() {
    this.loginError = '';
    this.passwordError = '';
    this.confirmPasswordError = '';
    this.firstNameError = '';
    this.lastNameError = '';
    this.emailError = '';
  }

}
