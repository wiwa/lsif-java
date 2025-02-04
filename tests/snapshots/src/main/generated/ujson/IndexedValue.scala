package ujson
//      ^^^^^ definition ujson/

import upickle.core.Util.reject
//     ^^^^^^^ reference upickle/
//             ^^^^ reference upickle/core/
//                  ^^^^ reference upickle/core/Util.
//                       ^^^^^^ reference upickle/core/Util.reject().
import scala.collection.mutable
//     ^^^^^ reference scala/
//           ^^^^^^^^^^ reference scala/collection/
//                      ^^^^^^^ reference scala/collection/mutable/
import upickle.core.{Visitor, ObjVisitor, ArrVisitor, Abort, AbortException}
//     ^^^^^^^ reference upickle/
//             ^^^^ reference upickle/core/
//                   ^^^^^^^ reference upickle/core/Visitor.
//                   ^^^^^^^ reference upickle/core/Visitor#
//                            ^^^^^^^^^^ reference upickle/core/ObjVisitor#
//                                        ^^^^^^^^^^ reference upickle/core/ArrVisitor#
//                                                    ^^^^^ reference upickle/core/Abort.
//                                                    ^^^^^ reference upickle/core/Abort#
//                                                           ^^^^^^^^^^^^^^ reference upickle/core/AbortException.
//                                                           ^^^^^^^^^^^^^^ reference upickle/core/AbortException#

/**
  * A version of [[ujson.Value]] that keeps the index positions of the various AST
  * nodes it is constructing. Usually not necessary, but sometimes useful if you
  * want to work with an AST but still provide source-index error positions if
  * something goes wrong
  */
sealed trait IndexedValue {
//           ^^^^^^^^^^^^ definition ujson/IndexedValue# sealed trait IndexedValue
  def index: Int
//    ^^^^^ definition ujson/IndexedValue#index(). def index: Int
//           ^^^ reference scala/Int#
}

object IndexedValue extends Transformer[IndexedValue]{
//     ^^^^^^^^^^^^ definition ujson/IndexedValue. object IndexedValue
//                          ^^^^^^^^^^^ reference ujson/Transformer#
//                                      ^^^^^^^^^^^^ reference ujson/IndexedValue#
//                                                    reference java/lang/Object#`<init>`().
  
  case class Str(index: Int, value0: java.lang.CharSequence) extends IndexedValue
//           ^^^ definition ujson/IndexedValue.Str# case class Str(index: Int, value0: CharSequence) extends IndexedValue
//           ^^^ synthetic_definition ujson/IndexedValue.Str#copy(). def copy(index: Int, value0: CharSequence): Str
//           ^^^ synthetic_definition ujson/IndexedValue.Str.apply(). def apply(index: Int, value0: CharSequence): Str
//           ^^^ definition ujson/IndexedValue.Str. object Str
//           ^^^ synthetic_definition ujson/IndexedValue.Str#productElement(). def productElement(x$1: Int): Any
//           ^^^ synthetic_definition ujson/IndexedValue.Str#productElementName(). def productElementName(x$1: Int): String
//               definition ujson/IndexedValue.Str#`<init>`(). def this(index: Int, value0: CharSequence)
//               ^^^^^ definition ujson/IndexedValue.Str#index. val index: Int
//               ^^^^^ definition ujson/IndexedValue.Str#copy().(index) default index: Int
//               ^^^^^ definition ujson/IndexedValue.Str#`<init>`().(index) index: Int
//               ^^^^^ definition ujson/IndexedValue.Str.apply().(index) index: Int
//                      ^^^ reference scala/Int#
//                           ^^^^^^ definition ujson/IndexedValue.Str#value0. val value0: CharSequence
//                           ^^^^^^ definition ujson/IndexedValue.Str#`<init>`().(value0) value0: CharSequence
//                           ^^^^^^ definition ujson/IndexedValue.Str.apply().(value0) value0: CharSequence
//                           ^^^^^^ definition ujson/IndexedValue.Str#copy().(value0) default value0: CharSequence
//                                   ^^^^ reference java/
//                                        ^^^^ reference java/lang/
//                                             ^^^^^^^^^^^^ reference java/lang/CharSequence#
//                                                                   ^^^^^^^^^^^^ reference ujson/IndexedValue#
//                                                                                reference java/lang/Object#`<init>`().
  case class Obj(index: Int, value0: (java.lang.CharSequence, IndexedValue)*) extends IndexedValue
//           ^^^ definition ujson/IndexedValue.Obj# case class Obj(index: Int, value0: (CharSequence, IndexedValue)*) extends IndexedValue
//           ^^^ synthetic_definition ujson/IndexedValue.Obj.apply(). def apply(index: Int, value0: (CharSequence, IndexedValue)*): Obj
//           ^^^ synthetic_definition ujson/IndexedValue.Obj#productElement(). def productElement(x$1: Int): Any
//           ^^^ definition ujson/IndexedValue.Obj. object Obj
//           ^^^ synthetic_definition ujson/IndexedValue.Obj#productElementName(). def productElementName(x$1: Int): String
//               definition ujson/IndexedValue.Obj#`<init>`(). def this(index: Int, value0: (CharSequence, IndexedValue)*)
//               ^^^^^ definition ujson/IndexedValue.Obj#index. val index: Int
//               ^^^^^ definition ujson/IndexedValue.Obj#`<init>`().(index) index: Int
//               ^^^^^ definition ujson/IndexedValue.Obj.apply().(index) index: Int
//                      ^^^ reference scala/Int#
//                           ^^^^^^ definition ujson/IndexedValue.Obj#value0. val value0: (CharSequence, IndexedValue)*
//                           ^^^^^^ definition ujson/IndexedValue.Obj.apply().(value0) value0: (CharSequence, IndexedValue)*
//                           ^^^^^^ definition ujson/IndexedValue.Obj#`<init>`().(value0) value0: (CharSequence, IndexedValue)*
//                                    ^^^^ reference java/
//                                         ^^^^ reference java/lang/
//                                              ^^^^^^^^^^^^ reference java/lang/CharSequence#
//                                                            ^^^^^^^^^^^^ reference ujson/IndexedValue#
//                                                                                    ^^^^^^^^^^^^ reference ujson/IndexedValue#
//                                                                                                 reference java/lang/Object#`<init>`().
  case class Arr(index: Int, value: IndexedValue*) extends IndexedValue
//           ^^^ definition ujson/IndexedValue.Arr# case class Arr(index: Int, value: IndexedValue*) extends IndexedValue
//           ^^^ synthetic_definition ujson/IndexedValue.Arr#productElementName(). def productElementName(x$1: Int): String
//           ^^^ synthetic_definition ujson/IndexedValue.Arr#productElement(). def productElement(x$1: Int): Any
//           ^^^ definition ujson/IndexedValue.Arr. object Arr
//           ^^^ synthetic_definition ujson/IndexedValue.Arr.apply(). def apply(index: Int, value: IndexedValue*): Arr
//               definition ujson/IndexedValue.Arr#`<init>`(). def this(index: Int, value: IndexedValue*)
//               ^^^^^ definition ujson/IndexedValue.Arr#index. val index: Int
//               ^^^^^ definition ujson/IndexedValue.Arr.apply().(index) index: Int
//               ^^^^^ definition ujson/IndexedValue.Arr#`<init>`().(index) index: Int
//                      ^^^ reference scala/Int#
//                           ^^^^^ definition ujson/IndexedValue.Arr#value. val value: IndexedValue*
//                           ^^^^^ definition ujson/IndexedValue.Arr#`<init>`().(value) value: IndexedValue*
//                           ^^^^^ definition ujson/IndexedValue.Arr.apply().(value) value: IndexedValue*
//                                  ^^^^^^^^^^^^ reference ujson/IndexedValue#
//                                                         ^^^^^^^^^^^^ reference ujson/IndexedValue#
//                                                                      reference java/lang/Object#`<init>`().
  case class Num(index: Int, s: CharSequence, decIndex: Int, expIndex: Int) extends IndexedValue
//           ^^^ definition ujson/IndexedValue.Num# case class Num(index: Int, s: CharSequence, decIndex: Int, expIndex: Int) extends IndexedValue
//           ^^^ definition ujson/IndexedValue.Num. object Num
//           ^^^ synthetic_definition ujson/IndexedValue.Num#copy(). def copy(index: Int, s: CharSequence, decIndex: Int, expIndex: Int): Num
//           ^^^ synthetic_definition ujson/IndexedValue.Num#productElement(). def productElement(x$1: Int): Any
//           ^^^ synthetic_definition ujson/IndexedValue.Num#productElementName(). def productElementName(x$1: Int): String
//           ^^^ synthetic_definition ujson/IndexedValue.Num.apply(). def apply(index: Int, s: CharSequence, decIndex: Int, expIndex: Int): Num
//               definition ujson/IndexedValue.Num#`<init>`(). def this(index: Int, s: CharSequence, decIndex: Int, expIndex: Int)
//               ^^^^^ definition ujson/IndexedValue.Num#index. val index: Int
//               ^^^^^ definition ujson/IndexedValue.Num#copy().(index) default index: Int
//               ^^^^^ definition ujson/IndexedValue.Num#`<init>`().(index) index: Int
//               ^^^^^ definition ujson/IndexedValue.Num.apply().(index) index: Int
//                      ^^^ reference scala/Int#
//                           ^ definition ujson/IndexedValue.Num#s. val s: CharSequence
//                           ^ definition ujson/IndexedValue.Num.apply().(s) s: CharSequence
//                           ^ definition ujson/IndexedValue.Num#`<init>`().(s) s: CharSequence
//                           ^ definition ujson/IndexedValue.Num#copy().(s) default s: CharSequence
//                              ^^^^^^^^^^^^ reference java/lang/CharSequence#
//                                            ^^^^^^^^ definition ujson/IndexedValue.Num#decIndex. val decIndex: Int
//                                            ^^^^^^^^ definition ujson/IndexedValue.Num#copy().(decIndex) default decIndex: Int
//                                            ^^^^^^^^ definition ujson/IndexedValue.Num.apply().(decIndex) decIndex: Int
//                                            ^^^^^^^^ definition ujson/IndexedValue.Num#`<init>`().(decIndex) decIndex: Int
//                                                      ^^^ reference scala/Int#
//                                                           ^^^^^^^^ definition ujson/IndexedValue.Num#expIndex. val expIndex: Int
//                                                           ^^^^^^^^ definition ujson/IndexedValue.Num#`<init>`().(expIndex) expIndex: Int
//                                                           ^^^^^^^^ definition ujson/IndexedValue.Num.apply().(expIndex) expIndex: Int
//                                                           ^^^^^^^^ definition ujson/IndexedValue.Num#copy().(expIndex) default expIndex: Int
//                                                                     ^^^ reference scala/Int#
//                                                                                  ^^^^^^^^^^^^ reference ujson/IndexedValue#
//                                                                                               reference java/lang/Object#`<init>`().
  case class NumRaw(index: Int, d: Double) extends IndexedValue
//           ^^^^^^ definition ujson/IndexedValue.NumRaw# case class NumRaw(index: Int, d: Double) extends IndexedValue
//           ^^^^^^ synthetic_definition ujson/IndexedValue.NumRaw#copy(). def copy(index: Int, d: Double): NumRaw
//           ^^^^^^ definition ujson/IndexedValue.NumRaw. object NumRaw
//           ^^^^^^ synthetic_definition ujson/IndexedValue.NumRaw#productElement(). def productElement(x$1: Int): Any
//           ^^^^^^ synthetic_definition ujson/IndexedValue.NumRaw.apply(). def apply(index: Int, d: Double): NumRaw
//           ^^^^^^ synthetic_definition ujson/IndexedValue.NumRaw#productElementName(). def productElementName(x$1: Int): String
//                  definition ujson/IndexedValue.NumRaw#`<init>`(). def this(index: Int, d: Double)
//                  ^^^^^ definition ujson/IndexedValue.NumRaw#index. val index: Int
//                  ^^^^^ definition ujson/IndexedValue.NumRaw.apply().(index) index: Int
//                  ^^^^^ definition ujson/IndexedValue.NumRaw#`<init>`().(index) index: Int
//                  ^^^^^ definition ujson/IndexedValue.NumRaw#copy().(index) default index: Int
//                         ^^^ reference scala/Int#
//                              ^ definition ujson/IndexedValue.NumRaw#d. val d: Double
//                              ^ definition ujson/IndexedValue.NumRaw#copy().(d) default d: Double
//                              ^ definition ujson/IndexedValue.NumRaw.apply().(d) d: Double
//                              ^ definition ujson/IndexedValue.NumRaw#`<init>`().(d) d: Double
//                                 ^^^^^^ reference scala/Double#
//                                                 ^^^^^^^^^^^^ reference ujson/IndexedValue#
//                                                              reference java/lang/Object#`<init>`().
  case class False(index: Int) extends IndexedValue{
//           ^^^^^ definition ujson/IndexedValue.False# case class False(index: Int) extends IndexedValue
//           ^^^^^ definition ujson/IndexedValue.False. object False
//           ^^^^^ synthetic_definition ujson/IndexedValue.False#productElementName(). def productElementName(x$1: Int): String
//           ^^^^^ synthetic_definition ujson/IndexedValue.False.apply(). def apply(index: Int): False
//           ^^^^^ synthetic_definition ujson/IndexedValue.False#productElement(). def productElement(x$1: Int): Any
//           ^^^^^ synthetic_definition ujson/IndexedValue.False#copy(). def copy(index: Int): False
//                 definition ujson/IndexedValue.False#`<init>`(). def this(index: Int)
//                 ^^^^^ definition ujson/IndexedValue.False#index. val index: Int
//                 ^^^^^ definition ujson/IndexedValue.False.apply().(index) index: Int
//                 ^^^^^ definition ujson/IndexedValue.False#copy().(index) default index: Int
//                 ^^^^^ definition ujson/IndexedValue.False#`<init>`().(index) index: Int
//                        ^^^ reference scala/Int#
//                                     ^^^^^^^^^^^^ reference ujson/IndexedValue#
//                                                  reference java/lang/Object#`<init>`().
    def value = false
//      ^^^^^ definition ujson/IndexedValue.False#value(). def value: Boolean
  }
  case class True(index: Int) extends IndexedValue{
//           ^^^^ definition ujson/IndexedValue.True# case class True(index: Int) extends IndexedValue
//           ^^^^ synthetic_definition ujson/IndexedValue.True#productElement(). def productElement(x$1: Int): Any
//           ^^^^ definition ujson/IndexedValue.True. object True
//           ^^^^ synthetic_definition ujson/IndexedValue.True#copy(). def copy(index: Int): True
//           ^^^^ synthetic_definition ujson/IndexedValue.True.apply(). def apply(index: Int): True
//           ^^^^ synthetic_definition ujson/IndexedValue.True#productElementName(). def productElementName(x$1: Int): String
//                definition ujson/IndexedValue.True#`<init>`(). def this(index: Int)
//                ^^^^^ definition ujson/IndexedValue.True#index. val index: Int
//                ^^^^^ definition ujson/IndexedValue.True.apply().(index) index: Int
//                ^^^^^ definition ujson/IndexedValue.True#`<init>`().(index) index: Int
//                ^^^^^ definition ujson/IndexedValue.True#copy().(index) default index: Int
//                       ^^^ reference scala/Int#
//                                    ^^^^^^^^^^^^ reference ujson/IndexedValue#
//                                                 reference java/lang/Object#`<init>`().
    def value = true
//      ^^^^^ definition ujson/IndexedValue.True#value(). def value: Boolean
  }
  case class Null(index: Int) extends IndexedValue{
//           ^^^^ definition ujson/IndexedValue.Null# case class Null(index: Int) extends IndexedValue
//           ^^^^ synthetic_definition ujson/IndexedValue.Null#copy(). def copy(index: Int): Null
//           ^^^^ synthetic_definition ujson/IndexedValue.Null#productElement(). def productElement(x$1: Int): Any
//           ^^^^ synthetic_definition ujson/IndexedValue.Null#productElementName(). def productElementName(x$1: Int): String
//           ^^^^ synthetic_definition ujson/IndexedValue.Null.apply(). def apply(index: Int): Null
//           ^^^^ definition ujson/IndexedValue.Null. object Null
//                definition ujson/IndexedValue.Null#`<init>`(). def this(index: Int)
//                ^^^^^ definition ujson/IndexedValue.Null#index. val index: Int
//                ^^^^^ definition ujson/IndexedValue.Null#`<init>`().(index) index: Int
//                ^^^^^ definition ujson/IndexedValue.Null.apply().(index) index: Int
//                ^^^^^ definition ujson/IndexedValue.Null#copy().(index) default index: Int
//                       ^^^ reference scala/Int#
//                                    ^^^^^^^^^^^^ reference ujson/IndexedValue#
//                                                 reference java/lang/Object#`<init>`().
    def value = null
//      ^^^^^ definition ujson/IndexedValue.Null#value(). def value: Null
  }

  def transform[T](j: IndexedValue, f: Visitor[_, T]): T = try{
//    ^^^^^^^^^ definition ujson/IndexedValue.transform(). def transform(j: IndexedValue, f: Visitor[local16, T[): T
//              ^ definition ujson/IndexedValue.transform().[T] T
//                 ^ definition ujson/IndexedValue.transform().(j) j: IndexedValue
//                    ^^^^^^^^^^^^ reference ujson/IndexedValue#
//                                  ^ definition ujson/IndexedValue.transform().(f) f: Visitor[local16, T[
//                                     ^^^^^^^ reference upickle/core/Visitor#
//                                                ^ reference ujson/IndexedValue.transform().[T]
//                                                     ^ reference ujson/IndexedValue.transform().[T]
    j match{
//  ^ reference ujson/IndexedValue.transform().(j)
      case IndexedValue.Null(i) => f.visitNull(i)
//         ^^^^^^^^^^^^ reference ujson/IndexedValue.
//                      ^^^^ reference ujson/IndexedValue.Null.
//                           ^ definition local17 i: Int
//                                 ^ reference ujson/IndexedValue.transform().(f)
//                                   ^^^^^^^^^ reference upickle/core/Visitor#visitNull().
//                                             ^ reference local17
      case IndexedValue.True(i) => f.visitTrue(i)
//         ^^^^^^^^^^^^ reference ujson/IndexedValue.
//                      ^^^^ reference ujson/IndexedValue.True.
//                           ^ definition local18 i: Int
//                                 ^ reference ujson/IndexedValue.transform().(f)
//                                   ^^^^^^^^^ reference upickle/core/Visitor#visitTrue().
//                                             ^ reference local18
      case IndexedValue.False(i) => f.visitFalse(i)
//         ^^^^^^^^^^^^ reference ujson/IndexedValue.
//                      ^^^^^ reference ujson/IndexedValue.False.
//                            ^ definition local19 i: Int
//                                  ^ reference ujson/IndexedValue.transform().(f)
//                                    ^^^^^^^^^^ reference upickle/core/Visitor#visitFalse().
//                                               ^ reference local19
      case IndexedValue.Str(i, s) => f.visitString(s, i)
//         ^^^^^^^^^^^^ reference ujson/IndexedValue.
//                      ^^^ reference ujson/IndexedValue.Str.
//                          ^ definition local20 i: Int
//                             ^ definition local21 s: CharSequence
//                                   ^ reference ujson/IndexedValue.transform().(f)
//                                     ^^^^^^^^^^^ reference upickle/core/Visitor#visitString().
//                                                 ^ reference local21
//                                                    ^ reference local20
      case IndexedValue.Num(i, s, d, e) => f.visitFloat64StringParts(s, d, e, i)
//         ^^^^^^^^^^^^ reference ujson/IndexedValue.
//                      ^^^ reference ujson/IndexedValue.Num.
//                          ^ definition local22 i: Int
//                             ^ definition local23 s: CharSequence
//                                ^ definition local24 d: Int
//                                   ^ definition local25 e: Int
//                                         ^ reference ujson/IndexedValue.transform().(f)
//                                           ^^^^^^^^^^^^^^^^^^^^^^^ reference upickle/core/Visitor#visitFloat64StringParts().
//                                                                   ^ reference local23
//                                                                      ^ reference local24
//                                                                         ^ reference local25
//                                                                            ^ reference local22
      case IndexedValue.NumRaw(i, d) => f.visitFloat64(d, i)
//         ^^^^^^^^^^^^ reference ujson/IndexedValue.
//                      ^^^^^^ reference ujson/IndexedValue.NumRaw.
//                             ^ definition local26 i: Int
//                                ^ definition local27 d: Double
//                                      ^ reference ujson/IndexedValue.transform().(f)
//                                        ^^^^^^^^^^^^ reference upickle/core/Visitor#visitFloat64().
//                                                     ^ reference local27
//                                                        ^ reference local26
      case IndexedValue.Arr(i, items @_*) =>
//         ^^^^^^^^^^^^ reference ujson/IndexedValue.
//                      ^^^ reference ujson/IndexedValue.Arr.
//                          ^ definition local28 i: Int
//                             ^^^^^ definition local29 items: Seq[IndexedValue]
        val ctx = f.visitArray(-1, -1).narrow
//          ^^^ definition local30 ctx: ArrVisitor[Any, T]
//                ^ reference ujson/IndexedValue.transform().(f)
//                  ^^^^^^^^^^ reference upickle/core/Visitor#visitArray().
//                                     ^^^^^^ reference upickle/core/ArrVisitor#narrow().
        for(item <- items) try ctx.visitValue(transform(item, ctx.subVisitor), item.index) catch reject(item.index)
//          ^^^^ definition local31 item: IndexedValue
//                  ^^^^^ reference local29
//                             ^^^ reference local30
//                                 ^^^^^^^^^^ reference upickle/core/ObjArrVisitor#visitValue().
//                                            ^^^^^^^^^ reference ujson/IndexedValue.transform().
//                                                      ^^^^ reference local31
//                                                            ^^^ reference local30
//                                                                ^^^^^^^^^^ reference upickle/core/ObjArrVisitor#subVisitor().
//                                                                             ^^^^ reference local31
//                                                                                  ^^^^^ reference ujson/IndexedValue#index().
//                                                                                               ^^^^^^ reference upickle/core/Util.reject().
//                                                                                                      ^^^^ reference local31
//                                                                                                           ^^^^^ reference ujson/IndexedValue#index().
        ctx.visitEnd(i)
//      ^^^ reference local30
//          ^^^^^^^^ reference upickle/core/ObjArrVisitor#visitEnd().
//                   ^ reference local28
      case IndexedValue.Obj(i, items @_*) =>
//         ^^^^^^^^^^^^ reference ujson/IndexedValue.
//                      ^^^ reference ujson/IndexedValue.Obj.
//                          ^ definition local34 i: Int
//                             ^^^^^ definition local35 items: Seq[(CharSequence, IndexedValue)]
        val ctx = f.visitObject(-1, -1).narrow
//          ^^^ definition local36 ctx: ObjVisitor[Any, T]
//                ^ reference ujson/IndexedValue.transform().(f)
//                  ^^^^^^^^^^^ reference upickle/core/Visitor#visitObject().
//                                      ^^^^^^ reference upickle/core/ObjVisitor#narrow().
        for((k, item) <- items) {
//           ^ definition local37 k: CharSequence
//              ^^^^ definition local38 item: IndexedValue
//                       ^^^^^ reference scala/collection/IterableOps#withFilter().
          val keyVisitor = try ctx.visitKey(i) catch reject(i)
//            ^^^^^^^^^^ definition local40 keyVisitor: Visitor[local41, Any[
//                             ^^^ reference local36
//                                 ^^^^^^^^ reference upickle/core/ObjVisitor#visitKey().
//                                          ^ reference local34
//                                                   ^^^^^^ reference upickle/core/Util.reject().
//                                                          ^ reference local34

          ctx.visitKeyValue(keyVisitor.visitString(k, i))
//        ^^^ reference local36
//            ^^^^^^^^^^^^^ reference upickle/core/ObjVisitor#visitKeyValue().
//                          ^^^^^^^^^^ reference local40
//                                     ^^^^^^^^^^^ reference upickle/core/Visitor#visitString().
//                                                 ^ reference local37
//                                                    ^ reference local34
          try ctx.visitValue(transform(item, ctx.subVisitor), item.index) catch reject(item.index)
//            ^^^ reference local36
//                ^^^^^^^^^^ reference upickle/core/ObjArrVisitor#visitValue().
//                           ^^^^^^^^^ reference ujson/IndexedValue.transform().
//                                     ^^^^ reference local38
//                                           ^^^ reference local36
//                                               ^^^^^^^^^^ reference upickle/core/ObjArrVisitor#subVisitor().
//                                                            ^^^^ reference local38
//                                                                 ^^^^^ reference ujson/IndexedValue#index().
//                                                                              ^^^^^^ reference upickle/core/Util.reject().
//                                                                                     ^^^^ reference local38
//                                                                                          ^^^^^ reference ujson/IndexedValue#index().
        }
        ctx.visitEnd(i)
//      ^^^ reference local36
//          ^^^^^^^^ reference upickle/core/ObjArrVisitor#visitEnd().
//                   ^ reference local34
    }
  } catch reject(j.index)
//        ^^^^^^ reference upickle/core/Util.reject().
//               ^ reference ujson/IndexedValue.transform().(j)
//                 ^^^^^ reference ujson/IndexedValue#index().


  object Builder extends JsVisitor[IndexedValue, IndexedValue]{
//       ^^^^^^^ definition ujson/IndexedValue.Builder. object Builder
//                       ^^^^^^^^^ reference ujson/JsVisitor#
//                                 ^^^^^^^^^^^^ reference ujson/IndexedValue#
//                                               ^^^^^^^^^^^^ reference ujson/IndexedValue#
//                                                             reference java/lang/Object#`<init>`().
    def visitArray(length: Int, i: Int) = new ArrVisitor[IndexedValue, IndexedValue.Arr] {
//      ^^^^^^^^^^ definition ujson/IndexedValue.Builder.visitArray(). def visitArray(length: Int, i: Int): { def subVisitor: Builder }
//                 ^^^^^^ definition ujson/IndexedValue.Builder.visitArray().(length) length: Int
//                         ^^^ reference scala/Int#
//                              ^ definition ujson/IndexedValue.Builder.visitArray().(i) i: Int
//                                 ^^^ reference scala/Int#
//                                             definition local49 final class $anon
//                                            ^^^^^^^^^^ reference upickle/core/ArrVisitor#
//                                                       ^^^^^^^^^^^^ reference ujson/IndexedValue#
//                                                                     ^^^^^^^^^^^^ reference ujson/IndexedValue.
//                                                                                  ^^^ reference ujson/IndexedValue.Arr#
//                                                                                        reference java/lang/Object#`<init>`().
      val out = mutable.Buffer.empty[IndexedValue]
//        ^^^ definition local50 private val out: Buffer[IndexedValue]
//              ^^^^^^^ reference scala/collection/mutable/
//                      ^^^^^^ reference scala/collection/mutable/Buffer.
//                             ^^^^^ reference scala/collection/SeqFactory.Delegate#empty().
//                                   ^^^^^^^^^^^^ reference ujson/IndexedValue#
      def subVisitor = Builder
//        ^^^^^^^^^^ definition local48 def subVisitor: Builder
//                     ^^^^^^^ reference ujson/IndexedValue.Builder.
      def visitValue(v: IndexedValue, index: Int): Unit = {
//        ^^^^^^^^^^ definition local51 def visitValue(v: IndexedValue, index: Int): Unit
//                   ^ definition local53 v: IndexedValue
//                      ^^^^^^^^^^^^ reference ujson/IndexedValue#
//                                    ^^^^^ definition local54 index: Int
//                                           ^^^ reference scala/Int#
//                                                 ^^^^ reference scala/Unit#
        out.append(v)
//      ^^^ reference local50
//          ^^^^^^ reference scala/collection/mutable/Buffer#append().
//                 ^ reference local53
      }
      def visitEnd(index: Int): IndexedValue.Arr = IndexedValue.Arr(i, out.toSeq:_*)
//        ^^^^^^^^ definition local52 def visitEnd(index: Int): Arr
//                 ^^^^^ definition local55 index: Int
//                        ^^^ reference scala/Int#
//                              ^^^^^^^^^^^^ reference ujson/IndexedValue.
//                                           ^^^ reference ujson/IndexedValue.Arr#
//                                                 ^^^^^^^^^^^^ reference ujson/IndexedValue.
//                                                              ^^^ reference ujson/IndexedValue.Arr.
//                                                                  ^ reference ujson/IndexedValue.Builder.visitArray().(i)
//                                                                     ^^^ reference local50
//                                                                         ^^^^^ reference scala/collection/IterableOnceOps#toSeq().
    }

    def visitObject(length: Int, i: Int) = new ObjVisitor[IndexedValue, IndexedValue.Obj] {
//      ^^^^^^^^^^^ definition ujson/IndexedValue.Builder.visitObject(). def visitObject(length: Int, i: Int): { def subVisitor: Builder; def visitKey(index: Int): Builder }
//                  ^^^^^^ definition ujson/IndexedValue.Builder.visitObject().(length) length: Int
//                          ^^^ reference scala/Int#
//                               ^ definition ujson/IndexedValue.Builder.visitObject().(i) i: Int
//                                  ^^^ reference scala/Int#
//                                              definition local59 final class $anon
//                                             ^^^^^^^^^^ reference upickle/core/ObjVisitor#
//                                                        ^^^^^^^^^^^^ reference ujson/IndexedValue#
//                                                                      ^^^^^^^^^^^^ reference ujson/IndexedValue.
//                                                                                   ^^^ reference ujson/IndexedValue.Obj#
//                                                                                         reference java/lang/Object#`<init>`().
      val out = mutable.Buffer.empty[(String, IndexedValue)]
//        ^^^ definition local60 private val out: Buffer[(String, IndexedValue)]
//              ^^^^^^^ reference scala/collection/mutable/
//                      ^^^^^^ reference scala/collection/mutable/Buffer.
//                             ^^^^^ reference scala/collection/SeqFactory.Delegate#empty().
//                                    ^^^^^^ reference scala/Predef.String#
//                                            ^^^^^^^^^^^^ reference ujson/IndexedValue#
      var currentKey: String = _
//        ^^^^^^^^^^ definition local61 private var currentKey: String
//                    ^^^^^^ reference scala/Predef.String#
      def subVisitor = Builder
//        ^^^^^^^^^^ definition local56 def subVisitor: Builder
//                     ^^^^^^^ reference ujson/IndexedValue.Builder.
      def visitKey(index: Int) = IndexedValue.Builder
//        ^^^^^^^^ definition local57 def visitKey(index: Int): Builder
//                 ^^^^^ definition local58 index: Int
//                        ^^^ reference scala/Int#
//                               ^^^^^^^^^^^^ reference ujson/IndexedValue.
//                                            ^^^^^^^ reference ujson/IndexedValue.Builder.
      def visitKeyValue(s: Any): Unit = currentKey = s.asInstanceOf[IndexedValue.Str].value0.toString
//        ^^^^^^^^^^^^^ definition local63 def visitKeyValue(s: Any): Unit
//                      ^ definition local67 s: Any
//                         ^^^ reference scala/Any#
//                               ^^^^ reference scala/Unit#
//                                      ^^^^^^^^^^ reference local62
//                                                   ^ reference local67
//                                                     ^^^^^^^^^^^^ reference scala/Any#asInstanceOf().
//                                                                  ^^^^^^^^^^^^ reference ujson/IndexedValue.
//                                                                               ^^^ reference ujson/IndexedValue.Str#
//                                                                                    ^^^^^^ reference ujson/IndexedValue.Str#value0.
//                                                                                           ^^^^^^^^ reference java/lang/Object#toString().
      def visitValue(v: IndexedValue, index: Int): Unit = {
//        ^^^^^^^^^^ definition local64 def visitValue(v: IndexedValue, index: Int): Unit
//                   ^ definition local68 v: IndexedValue
//                      ^^^^^^^^^^^^ reference ujson/IndexedValue#
//                                    ^^^^^ definition local69 index: Int
//                                           ^^^ reference scala/Int#
//                                                 ^^^^ reference scala/Unit#
        out.append((currentKey, v))
//      ^^^ reference local60
//          ^^^^^^ reference scala/collection/mutable/Buffer#append().
//                  ^^^^^^^^^^ reference local61
//                              ^ reference local68
      }
      def visitEnd(index: Int): IndexedValue.Obj = IndexedValue.Obj(i, out.toSeq:_*)
//        ^^^^^^^^ definition local65 def visitEnd(index: Int): Obj
//                 ^^^^^ definition local70 index: Int
//                        ^^^ reference scala/Int#
//                              ^^^^^^^^^^^^ reference ujson/IndexedValue.
//                                           ^^^ reference ujson/IndexedValue.Obj#
//                                                 ^^^^^^^^^^^^ reference ujson/IndexedValue.
//                                                              ^^^ reference ujson/IndexedValue.Obj.
//                                                                  ^ reference ujson/IndexedValue.Builder.visitObject().(i)
//                                                                     ^^^ reference local60
//                                                                         ^^^^^ reference scala/collection/IterableOnceOps#toSeq().
    }

    def visitNull(i: Int) = IndexedValue.Null(i)
//      ^^^^^^^^^ definition ujson/IndexedValue.Builder.visitNull(). def visitNull(i: Int): Null
//                ^ definition ujson/IndexedValue.Builder.visitNull().(i) i: Int
//                   ^^^ reference scala/Int#
//                          ^^^^^^^^^^^^ reference ujson/IndexedValue.
//                                       ^^^^ reference ujson/IndexedValue.Null.
//                                            ^ reference ujson/IndexedValue.Builder.visitNull().(i)

    def visitFalse(i: Int) = IndexedValue.False(i)
//      ^^^^^^^^^^ definition ujson/IndexedValue.Builder.visitFalse(). def visitFalse(i: Int): False
//                 ^ definition ujson/IndexedValue.Builder.visitFalse().(i) i: Int
//                    ^^^ reference scala/Int#
//                           ^^^^^^^^^^^^ reference ujson/IndexedValue.
//                                        ^^^^^ reference ujson/IndexedValue.False.
//                                              ^ reference ujson/IndexedValue.Builder.visitFalse().(i)

    def visitTrue(i: Int) = IndexedValue.True(i)
//      ^^^^^^^^^ definition ujson/IndexedValue.Builder.visitTrue(). def visitTrue(i: Int): True
//                ^ definition ujson/IndexedValue.Builder.visitTrue().(i) i: Int
//                   ^^^ reference scala/Int#
//                          ^^^^^^^^^^^^ reference ujson/IndexedValue.
//                                       ^^^^ reference ujson/IndexedValue.True.
//                                            ^ reference ujson/IndexedValue.Builder.visitTrue().(i)

    def visitFloat64StringParts(s: CharSequence, decIndex: Int, expIndex: Int, i: Int) = IndexedValue.Num(i, s, decIndex, expIndex)
//      ^^^^^^^^^^^^^^^^^^^^^^^ definition ujson/IndexedValue.Builder.visitFloat64StringParts(). def visitFloat64StringParts(s: CharSequence, decIndex: Int, expIndex: Int, i: Int): Num
//                              ^ definition ujson/IndexedValue.Builder.visitFloat64StringParts().(s) s: CharSequence
//                                 ^^^^^^^^^^^^ reference java/lang/CharSequence#
//                                               ^^^^^^^^ definition ujson/IndexedValue.Builder.visitFloat64StringParts().(decIndex) decIndex: Int
//                                                         ^^^ reference scala/Int#
//                                                              ^^^^^^^^ definition ujson/IndexedValue.Builder.visitFloat64StringParts().(expIndex) expIndex: Int
//                                                                        ^^^ reference scala/Int#
//                                                                             ^ definition ujson/IndexedValue.Builder.visitFloat64StringParts().(i) i: Int
//                                                                                ^^^ reference scala/Int#
//                                                                                       ^^^^^^^^^^^^ reference ujson/IndexedValue.
//                                                                                                    ^^^ reference ujson/IndexedValue.Num.
//                                                                                                        ^ reference ujson/IndexedValue.Builder.visitFloat64StringParts().(i)
//                                                                                                           ^ reference ujson/IndexedValue.Builder.visitFloat64StringParts().(s)
//                                                                                                              ^^^^^^^^ reference ujson/IndexedValue.Builder.visitFloat64StringParts().(decIndex)
//                                                                                                                        ^^^^^^^^ reference ujson/IndexedValue.Builder.visitFloat64StringParts().(expIndex)
    override def visitFloat64(d: Double, i: Int) = IndexedValue.NumRaw(i, d)
//               ^^^^^^^^^^^^ definition ujson/IndexedValue.Builder.visitFloat64(). def visitFloat64(d: Double, i: Int): NumRaw
//                            ^ definition ujson/IndexedValue.Builder.visitFloat64().(d) d: Double
//                               ^^^^^^ reference scala/Double#
//                                       ^ definition ujson/IndexedValue.Builder.visitFloat64().(i) i: Int
//                                          ^^^ reference scala/Int#
//                                                 ^^^^^^^^^^^^ reference ujson/IndexedValue.
//                                                              ^^^^^^ reference ujson/IndexedValue.NumRaw.
//                                                                     ^ reference ujson/IndexedValue.Builder.visitFloat64().(i)
//                                                                        ^ reference ujson/IndexedValue.Builder.visitFloat64().(d)

    def visitString(s: CharSequence, i: Int) = IndexedValue.Str(i, s)
//      ^^^^^^^^^^^ definition ujson/IndexedValue.Builder.visitString(). def visitString(s: CharSequence, i: Int): Str
//                  ^ definition ujson/IndexedValue.Builder.visitString().(s) s: CharSequence
//                     ^^^^^^^^^^^^ reference java/lang/CharSequence#
//                                   ^ definition ujson/IndexedValue.Builder.visitString().(i) i: Int
//                                      ^^^ reference scala/Int#
//                                             ^^^^^^^^^^^^ reference ujson/IndexedValue.
//                                                          ^^^ reference ujson/IndexedValue.Str.
//                                                              ^ reference ujson/IndexedValue.Builder.visitString().(i)
//                                                                 ^ reference ujson/IndexedValue.Builder.visitString().(s)
  }
}
