public class Patient {
  private String name;
  private int age;
  private String illness;
  private Patient nextPatient;

  public Patient getNext() { return nextPatient; }
  public void setNext(Patient p) { nextPatient = p; }

  public Patient(String name, int age, String illness) {
   this.name = name;
   this.age = age;
   this.illness = illness;
   this.nextPatient = null;
  }

  public void showPatient() {
    System.out.println(name + ", " + age + ", " + illness);
  }

  public void showNth(int position) {
    nth(position).showPatient();
  }

  public void addPatient(Patient newPatient) {
    if (this.nextPatient == null) {
      this.nextPatient = newPatient;
    } else {
      this.nextPatient.addPatient(newPatient);
    } 
  }
  
  public void deleteNthPatient(int position) {
    deletePatient(nth(position));
  }

  public boolean deletePatient(Patient patient) {
    if (this.nextPatient == null) {
      return false;
    } else if (this.nextPatient.name.equals(patient.name)) {
      this.nextPatient = nextPatient.nextPatient;
      return true;
    } else {
      return this.nextPatient.deletePatient(patient);
    }
  }

  public Patient nth(int position) { // counting from 1
    if (position == 1) {
      return this;
    } else if ((position < 1) || (nextPatient == null)) {
      return null;
    } else {
      return nextPatient.nth(position - 1);
    }
  }
}
