package com.rishi.userbooking.DTO;

@FunctionalInterface
public interface ProcessableResource  {



    /**

     * Instantiate.

     *

     * @return the processable resource

     */

    ProcessableResource instantiate();

}
