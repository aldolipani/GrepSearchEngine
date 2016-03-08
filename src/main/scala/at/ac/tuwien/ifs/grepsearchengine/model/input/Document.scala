package at.ac.tuwien.ifs.grepsearchengine.model.input

import at.ac.tuwien.ifs.grepsearchengine.model.PreProcessableInput

/**
 * Created by aldo on 26/02/16.
 */
class Document(val id:String, val filePath:String) extends PreProcessableInput{

  override def toString = {
    s"""file path:\t${filePath}"""
  }

}

