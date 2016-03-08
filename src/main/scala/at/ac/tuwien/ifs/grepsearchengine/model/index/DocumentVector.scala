package at.ac.tuwien.ifs.grepsearchengine.model.index

import at.ac.tuwien.ifs.grepsearchengine.model.{TermsVector, PreProcessableInput}

/**
 * Created by aldo on 29/02/16.
 */
class DocumentVector(val id:String, override val terms:List[Term]) extends TermsVector(id, terms) {

  def ld = terms.map(_.v).sum

  def Td = terms.map(_.id).toSet

  def sTd = terms.size

  override def apply(f:(String) => String):DocumentVector = new DocumentVector (id, super.apply(f).terms)
}
