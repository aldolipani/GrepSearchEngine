package at.ac.tuwien.ifs.grepsearchengine.textpreprocessing

import at.ac.tuwien.ifs.grepsearchengine.model.PreProcessableInput

/**
 * Created by aldo on 26/02/16.
 */
abstract class Component {

  def preProcess(input:PreProcessableInput):PreProcessableInput

}
