package at.ac.tuwien.ifs.grepsearchengine.model

import at.ac.tuwien.ifs.grepsearchengine.model.index.Term

import scala.collection.mutable

/**
 * Created by aldo on 29/02/16.
 */

class Terms(id:String, val terms:List[Term]) extends PreProcessableInput{

  lazy val mTerms = terms.map(t => t.id -> t.v).toMap

  def this(id:String, mTerms:Map[String, Double]) = this(id, mTerms.map(e => new Term(e._1, e._2)).toList)

  override def toString: String = {terms.mkString(" ")}

  def apply(f:(String) => String):TermsVector = new TermsVector (id,
    terms.map(t => new Term(f(t.id), t.v)).filter(t => t.id!=null).groupBy(_.id).map(e => new Term(e._1, e._2.map(_.v).sum)).toList
  )

}

class TermsVector(id:String, terms:List[Term]) extends Terms(id, terms){

  def getTf(term:Term):Double = mTerms.getOrElse(term.id, 0d)

}

class TermsColumn(terms:List[Term]) extends TermsVector("IDF", terms) {

  def this(mTerms:Map[String, Double]) = this(mTerms.map(e => new Term(e._1, e._2)).toList)

  def getIdf(term:Term):Double = mTerms.getOrElse(term.id, 0d)

  override def toString: String = {terms.sortBy(-_.v).mkString("\n")}

}


class MutableTermsColumn(val uTerms:collection.mutable.Map[String, Double] = new mutable.LinkedHashMap()) extends TermsColumn(Nil) {

  override lazy val mTerms = Map(uTerms.toSeq: _*)

  override def toString: String = {uTerms.map(e => new Term(e._1, e._2)).toList.sortBy(e => -e.v -> e.id).mkString("\n")}

}
