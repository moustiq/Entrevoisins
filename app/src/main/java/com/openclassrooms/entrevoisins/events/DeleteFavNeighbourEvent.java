package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Event fired when a user deletes a Neighbour
 */
public class DeleteFavNeighbourEvent {

    /**
     * Neighbour to delete
     */
    public Neighbour favNeighbour;

    /**
     * Constructor.
     * @param neighbour
     */
    public DeleteFavNeighbourEvent(Neighbour neighbour) {
        this.favNeighbour = neighbour;
    }
}
