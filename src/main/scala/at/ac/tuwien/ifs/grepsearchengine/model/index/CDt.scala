package at.ac.tuwien.ifs.grepsearchengine.model.index

import at.ac.tuwien.ifs.grepsearchengine.model.index.types._

import scalaz.Scalaz._

/**
 * Created by aldo on 29/02/16.
 */

class CDt(val dts: Map[String, Dt] = Map()) {

  def add(tl: DocumentVector) = new CDt(
    dts |+| tl.terms.map(t => t.id -> Set(tl.id)).toMap)

  override def toString =
    dts.toList.sortBy(_._1).map(e => e._1 + "->" + e._2.toList.sorted.mkString(",")).mkString("\n")

  def getSCDt() = new SCDt(
    dts.mapValues(_.size.toDouble)
  )

  def getDt(t:Term) = dts.getOrElse(t.id, Set())

}

object CDt {

  def apply(): CDt = new CDt

  def get(tl: DocumentVector): CDt = CDt().add(tl)

  def get(ltl: List[DocumentVector]): CDt =
    ltl.foldRight(CDt())((tl, dt) => dt.add(tl))

}

class SCDt(val sdts: Map[String, SDt] = Map()) {

  def getSDt(t:Term) = sdts.getOrElse(t.id, 0d)

  override def toString =
    sdts.toList.sortBy(_._1).map(e => e._1 + "->" + e._2).mkString("\n")

}

object SCDt {

  def apply(): SCDt = new SCDt

  def get(dt: CDt): SCDt = dt.getSCDt()

}
