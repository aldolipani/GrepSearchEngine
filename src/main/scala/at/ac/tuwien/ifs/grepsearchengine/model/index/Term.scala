package at.ac.tuwien.ifs.grepsearchengine.model.index

/**
 * Created by aldo on 26/02/16.
 */
class Term(val id:String, val v:Double) {
  override def toString: String = {id + " -> " + v}
}