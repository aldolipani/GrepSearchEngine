package at.ac.tuwien.ifs.grepsearchengine.model.matching

import at.ac.tuwien.ifs.grepsearchengine.model.CollectionStats
import at.ac.tuwien.ifs.grepsearchengine.model.index._

/**
 * Created by aldo on 29/02/16.
 */
class BM25(val scdt:SCDt, colStats:CollectionStats, val pars:Map[String, Double] = Map(BM25.K1 -> 1.2d, BM25.B -> 0.7d)) extends ScoringModel{

  override def score(tv: TopicVector, dv: DocumentVector):Double = {
    val sD = colStats.sD
    val k1 = pars.get(BM25.K1).get
    val b = pars.get(BM25.B).get

    tv.terms.map(tt => {
      val tf_d = dv.getTf(tt)
      val sD_t = scdt.getSDt(tt)
      val TF = tf_d/(k1*b + tf_d)
      val IDF = Math.log(sD/sD_t)
      TF*IDF
    }).sum
  }

}

object BM25 {
  final val K1 = "k1"
  final val B = "b"
}
