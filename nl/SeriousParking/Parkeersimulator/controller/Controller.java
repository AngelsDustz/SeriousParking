package nl.SeriousParking.Parkeersimulator.controller;


import nl.SeriousParking.Parkeersimulator.model.Model;

abstract public class Controller<M extends Model> {

    protected M model;

    public Controller(M model) {
      this.model = model;
    }
  }

