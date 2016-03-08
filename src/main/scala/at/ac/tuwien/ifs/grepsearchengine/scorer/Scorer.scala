package at.ac.tuwien.ifs.grepsearchengine.scorer

import at.ac.tuwien.ifs.grepsearchengine.model.index.{DocumentVector, TopicVector}
import at.ac.tuwien.ifs.grepsearchengine.model.matching.ScoringModel

/**
 * Created by aldo on 29/02/16.
 */
class Scorer(model:ScoringModel, ds:List[DocumentVector]) {

  def score(tv: TopicVector): List[(String, Double)] =
    ds.map(dv => (dv.id, model.score(tv, dv))).sortBy(-_._2)

}
