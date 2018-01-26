package nl.SeriousParking.Parkeersimulator.controller;


import nl.SeriousParking.Parkeersimulator.model.Model;

/**
 * @param <M> Any kind of model.
 * @version 1.0.0
 */
abstract public class Controller<M extends Model> {
    protected M model;

    /**
     * @param model A model.
     */
    public Controller(M model) {
      this.model = model;
    }
  }

