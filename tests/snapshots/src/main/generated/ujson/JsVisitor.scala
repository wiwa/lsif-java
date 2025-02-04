package ujson
//      ^^^^^ definition ujson/

import upickle.core.{ArrVisitor, ObjVisitor, Visitor}
//     ^^^^^^^ reference upickle/
//             ^^^^ reference upickle/core/
//                   ^^^^^^^^^^ reference upickle/core/ArrVisitor#
//                               ^^^^^^^^^^ reference upickle/core/ObjVisitor#
//                                           ^^^^^^^ reference upickle/core/Visitor.
//                                           ^^^^^^^ reference upickle/core/Visitor#

/**
  * A [[Visitor]] specialized to work with JSON types. Forwards the
  * not-JSON-related methods to their JSON equivalents.
  */
trait JsVisitor[-T, +J] extends Visitor[T, J]{
//    ^^^^^^^^^ definition ujson/JsVisitor# trait JsVisitor[T, J]
//               ^ definition ujson/JsVisitor#[T] T
//                   ^ definition ujson/JsVisitor#[J] J
//                              ^^^^^^^ reference upickle/core/Visitor#
//                                      ^ reference ujson/JsVisitor#[T]
//                                         ^ reference ujson/JsVisitor#[J]
  def visitFloat64(d: Double, index: Int): J = {
//    ^^^^^^^^^^^^ definition ujson/JsVisitor#visitFloat64(). def visitFloat64(d: Double, index: Int): J
//                 ^ definition ujson/JsVisitor#visitFloat64().(d) d: Double
//                    ^^^^^^ reference scala/Double#
//                            ^^^^^ definition ujson/JsVisitor#visitFloat64().(index) index: Int
//                                   ^^^ reference scala/Int#
//                                         ^ reference ujson/JsVisitor#[J]
    val i = d.toLong
//      ^ definition local0 i: Long
//          ^ reference ujson/JsVisitor#visitFloat64().(d)
//            ^^^^^^ reference scala/Double#toLong().
    if(i == d) visitFloat64StringParts(i.toString, -1, -1, index)
//     ^ reference local0
//       ^^ reference scala/Long#`==`(+6).
//          ^ reference ujson/JsVisitor#visitFloat64().(d)
//             ^^^^^^^^^^^^^^^^^^^^^^^ reference upickle/core/Visitor#visitFloat64StringParts().
//                                     ^ reference local0
//                                       ^^^^^^^^ reference scala/Any#toString().
//                                                         ^^^^^ reference ujson/JsVisitor#visitFloat64().(index)
    else visitFloat64String(d.toString, index)
//       ^^^^^^^^^^^^^^^^^^ reference ujson/JsVisitor#visitFloat64String().
//                          ^ reference ujson/JsVisitor#visitFloat64().(d)
//                            ^^^^^^^^ reference scala/Any#toString().
//                                      ^^^^^ reference ujson/JsVisitor#visitFloat64().(index)

  }

  def visitFloat32(d: Float, index: Int): J = visitFloat64(d, index)
//    ^^^^^^^^^^^^ definition ujson/JsVisitor#visitFloat32(). def visitFloat32(d: Float, index: Int): J
//                 ^ definition ujson/JsVisitor#visitFloat32().(d) d: Float
//                    ^^^^^ reference scala/Float#
//                           ^^^^^ definition ujson/JsVisitor#visitFloat32().(index) index: Int
//                                  ^^^ reference scala/Int#
//                                        ^ reference ujson/JsVisitor#[J]
//                                            ^^^^^^^^^^^^ reference ujson/JsVisitor#visitFloat64().
//                                                         ^ reference scala/Float#toDouble().
//                                                            ^^^^^ reference ujson/JsVisitor#visitFloat32().(index)
  def visitInt32(i: Int, index: Int): J = visitFloat64(i, index)
//    ^^^^^^^^^^ definition ujson/JsVisitor#visitInt32(). def visitInt32(i: Int, index: Int): J
//               ^ definition ujson/JsVisitor#visitInt32().(i) i: Int
//                  ^^^ reference scala/Int#
//                       ^^^^^ definition ujson/JsVisitor#visitInt32().(index) index: Int
//                              ^^^ reference scala/Int#
//                                    ^ reference ujson/JsVisitor#[J]
//                                        ^^^^^^^^^^^^ reference ujson/JsVisitor#visitFloat64().
//                                                     ^ reference scala/Int#toDouble().
//                                                        ^^^^^ reference ujson/JsVisitor#visitInt32().(index)
  def visitInt64(i: Long, index: Int): J = {
//    ^^^^^^^^^^ definition ujson/JsVisitor#visitInt64(). def visitInt64(i: Long, index: Int): J
//               ^ definition ujson/JsVisitor#visitInt64().(i) i: Long
//                  ^^^^ reference scala/Long#
//                        ^^^^^ definition ujson/JsVisitor#visitInt64().(index) index: Int
//                               ^^^ reference scala/Int#
//                                     ^ reference ujson/JsVisitor#[J]
    if (math.abs(i) > math.pow(2, 53) || i == -9223372036854775808L) visitString(i.toString, index)
//      ^^^^ reference scala/math/
//           ^^^ reference scala/math/package.abs(+1).
//               ^ reference ujson/JsVisitor#visitInt64().(i)
//                  ^ reference scala/Long#`>`(+6).
//                    ^^^^ reference scala/math/
//                         ^^^ reference scala/math/package.pow().
//                                    ^^ reference scala/Boolean#`||`().
//                                       ^ reference ujson/JsVisitor#visitInt64().(i)
//                                         ^^ reference scala/Long#`==`(+4).
//                                                                   ^^^^^^^^^^^ reference upickle/core/Visitor#visitString().
//                                                                               ^ reference ujson/JsVisitor#visitInt64().(i)
//                                                                                 ^^^^^^^^ reference scala/Any#toString().
//                                                                                           ^^^^^ reference ujson/JsVisitor#visitInt64().(index)
    else visitFloat64(i, index)
//       ^^^^^^^^^^^^ reference ujson/JsVisitor#visitFloat64().
//                    ^ reference scala/Long#toDouble().
//                       ^^^^^ reference ujson/JsVisitor#visitInt64().(index)
  }
  def visitUInt64(i: Long, index: Int): J = {
//    ^^^^^^^^^^^ definition ujson/JsVisitor#visitUInt64(). def visitUInt64(i: Long, index: Int): J
//                ^ definition ujson/JsVisitor#visitUInt64().(i) i: Long
//                   ^^^^ reference scala/Long#
//                         ^^^^^ definition ujson/JsVisitor#visitUInt64().(index) index: Int
//                                ^^^ reference scala/Int#
//                                      ^ reference ujson/JsVisitor#[J]
    if (i > math.pow(2, 53) || i < 0) visitString(java.lang.Long.toUnsignedString(i), index)
//      ^ reference ujson/JsVisitor#visitUInt64().(i)
//        ^ reference scala/Long#`>`(+6).
//          ^^^^ reference scala/math/
//               ^^^ reference scala/math/package.pow().
//                          ^^ reference scala/Boolean#`||`().
//                             ^ reference ujson/JsVisitor#visitUInt64().(i)
//                               ^ reference scala/Long#`<`(+3).
//                                    ^^^^^^^^^^^ reference upickle/core/Visitor#visitString().
//                                                ^^^^ reference java/
//                                                     ^^^^ reference java/lang/
//                                                          ^^^^ reference java/lang/Long#
//                                                               ^^^^^^^^^^^^^^^^ reference java/lang/Long#toUnsignedString(+1).
//                                                                                ^ reference ujson/JsVisitor#visitUInt64().(i)
//                                                                                    ^^^^^ reference ujson/JsVisitor#visitUInt64().(index)
    else visitFloat64(i, index)
//       ^^^^^^^^^^^^ reference ujson/JsVisitor#visitFloat64().
//                    ^ reference scala/Long#toDouble().
//                       ^^^^^ reference ujson/JsVisitor#visitUInt64().(index)
  }

  def visitFloat64String(s: String, index: Int): J = {
//    ^^^^^^^^^^^^^^^^^^ definition ujson/JsVisitor#visitFloat64String(). def visitFloat64String(s: String, index: Int): J
//                       ^ definition ujson/JsVisitor#visitFloat64String().(s) s: String
//                          ^^^^^^ reference scala/Predef.String#
//                                  ^^^^^ definition ujson/JsVisitor#visitFloat64String().(index) index: Int
//                                         ^^^ reference scala/Int#
//                                               ^ reference ujson/JsVisitor#[J]
    visitFloat64StringParts(
//  ^^^^^^^^^^^^^^^^^^^^^^^ reference upickle/core/Visitor#visitFloat64StringParts().
      s,
//    ^ reference ujson/JsVisitor#visitFloat64String().(s)
      s.indexOf('.'),
//    ^ reference ujson/JsVisitor#visitFloat64String().(s)
//      ^^^^^^^ reference java/lang/String#indexOf().
      s.indexOf('E') match{
//    ^ reference ujson/JsVisitor#visitFloat64String().(s)
//      ^^^^^^^ reference java/lang/String#indexOf().
        case -1 => s.indexOf('e')
//                 ^ reference ujson/JsVisitor#visitFloat64String().(s)
//                   ^^^^^^^ reference java/lang/String#indexOf().
        case n => n
//           ^ definition local1 n: Int
//                ^ reference local1
      },
      -1
    )
  }

  def visitBinary(bytes: Array[Byte], offset: Int, len: Int, index: Int): J = {
//    ^^^^^^^^^^^ definition ujson/JsVisitor#visitBinary(). def visitBinary(bytes: Array[Byte], offset: Int, len: Int, index: Int): J
//                ^^^^^ definition ujson/JsVisitor#visitBinary().(bytes) bytes: Array[Byte]
//                       ^^^^^ reference scala/Array#
//                             ^^^^ reference scala/Byte#
//                                    ^^^^^^ definition ujson/JsVisitor#visitBinary().(offset) offset: Int
//                                            ^^^ reference scala/Int#
//                                                 ^^^ definition ujson/JsVisitor#visitBinary().(len) len: Int
//                                                      ^^^ reference scala/Int#
//                                                           ^^^^^ definition ujson/JsVisitor#visitBinary().(index) index: Int
//                                                                  ^^^ reference scala/Int#
//                                                                        ^ reference ujson/JsVisitor#[J]
    val arr = visitArray(len, index)
//      ^^^ definition local2 arr: ArrVisitor[T, J]
//            ^^^^^^^^^^ reference upickle/core/Visitor#visitArray().
//                       ^^^ reference ujson/JsVisitor#visitBinary().(len)
//                            ^^^^^ reference ujson/JsVisitor#visitBinary().(index)
    var i = 0
//      ^ definition local3 i: Int
    while (i < len){
//         ^ reference local3
//           ^ reference scala/Int#`<`(+3).
//             ^^^ reference ujson/JsVisitor#visitBinary().(len)
      arr.visitValue(arr.subVisitor.visitInt32(bytes(offset + i), index).asInstanceOf[T], index)
//    ^^^ reference local2
//        ^^^^^^^^^^ reference upickle/core/ObjArrVisitor#visitValue().
//                   ^^^ reference local2
//                       ^^^^^^^^^^ reference upickle/core/ObjArrVisitor#subVisitor().
//                                  ^^^^^^^^^^ reference upickle/core/Visitor#visitInt32().
//                                             ^^^^^ reference ujson/JsVisitor#visitBinary().(bytes)
//                                                   ^^^^^^ reference ujson/JsVisitor#visitBinary().(offset)
//                                                          ^ reference scala/Int#`+`(+4).
//                                                            ^ reference local3
//                                                                ^^^^^ reference ujson/JsVisitor#visitBinary().(index)
//                                                                       ^^^^^^^^^^^^ reference scala/Any#asInstanceOf().
//                                                                                    ^ reference ujson/JsVisitor#[T]
//                                                                                        ^^^^^ reference ujson/JsVisitor#visitBinary().(index)
      i += 1
//    ^ reference local3
//      ^^ reference scala/Int#`+`(+4).
    }
    arr.visitEnd(index)
//  ^^^ reference local2
//      ^^^^^^^^ reference upickle/core/ObjArrVisitor#visitEnd().
//               ^^^^^ reference ujson/JsVisitor#visitBinary().(index)
  }

  def visitFloat64StringParts(s: CharSequence, decIndex: Int, expIndex: Int): J = visitFloat64StringParts(s, decIndex, expIndex, -1)
//    ^^^^^^^^^^^^^^^^^^^^^^^ definition ujson/JsVisitor#visitFloat64StringParts(). def visitFloat64StringParts(s: CharSequence, decIndex: Int, expIndex: Int): J
//                            ^ definition ujson/JsVisitor#visitFloat64StringParts().(s) s: CharSequence
//                               ^^^^^^^^^^^^ reference java/lang/CharSequence#
//                                             ^^^^^^^^ definition ujson/JsVisitor#visitFloat64StringParts().(decIndex) decIndex: Int
//                                                       ^^^ reference scala/Int#
//                                                            ^^^^^^^^ definition ujson/JsVisitor#visitFloat64StringParts().(expIndex) expIndex: Int
//                                                                      ^^^ reference scala/Int#
//                                                                            ^ reference ujson/JsVisitor#[J]
//                                                                                ^^^^^^^^^^^^^^^^^^^^^^^ reference upickle/core/Visitor#visitFloat64StringParts().
//                                                                                                        ^ reference ujson/JsVisitor#visitFloat64StringParts().(s)
//                                                                                                           ^^^^^^^^ reference ujson/JsVisitor#visitFloat64StringParts().(decIndex)
//                                                                                                                     ^^^^^^^^ reference ujson/JsVisitor#visitFloat64StringParts().(expIndex)

  def visitExt(tag: Byte, bytes: Array[Byte], offset: Int, len: Int, index: Int): J = {
//    ^^^^^^^^ definition ujson/JsVisitor#visitExt(). def visitExt(tag: Byte, bytes: Array[Byte], offset: Int, len: Int, index: Int): J
//             ^^^ definition ujson/JsVisitor#visitExt().(tag) tag: Byte
//                  ^^^^ reference scala/Byte#
//                        ^^^^^ definition ujson/JsVisitor#visitExt().(bytes) bytes: Array[Byte]
//                               ^^^^^ reference scala/Array#
//                                     ^^^^ reference scala/Byte#
//                                            ^^^^^^ definition ujson/JsVisitor#visitExt().(offset) offset: Int
//                                                    ^^^ reference scala/Int#
//                                                         ^^^ definition ujson/JsVisitor#visitExt().(len) len: Int
//                                                              ^^^ reference scala/Int#
//                                                                   ^^^^^ definition ujson/JsVisitor#visitExt().(index) index: Int
//                                                                          ^^^ reference scala/Int#
//                                                                                ^ reference ujson/JsVisitor#[J]
    val arr = visitArray(-1, index)
//      ^^^ definition local5 arr: ArrVisitor[T, J]
//            ^^^^^^^^^^ reference upickle/core/Visitor#visitArray().
//                           ^^^^^ reference ujson/JsVisitor#visitExt().(index)
    arr.visitValue(visitFloat64(tag, index).asInstanceOf[T], -1)
//  ^^^ reference local5
//      ^^^^^^^^^^ reference upickle/core/ObjArrVisitor#visitValue().
//                 ^^^^^^^^^^^^ reference ujson/JsVisitor#visitFloat64().
//                              ^^^ reference scala/Byte#toDouble().
//                                   ^^^^^ reference ujson/JsVisitor#visitExt().(index)
//                                          ^^^^^^^^^^^^ reference scala/Any#asInstanceOf().
//                                                       ^ reference ujson/JsVisitor#[T]
    arr.visitValue(visitBinary(bytes, offset, len, index).asInstanceOf[T], -1)
//  ^^^ reference local5
//      ^^^^^^^^^^ reference upickle/core/ObjArrVisitor#visitValue().
//                 ^^^^^^^^^^^ reference ujson/JsVisitor#visitBinary().
//                             ^^^^^ reference ujson/JsVisitor#visitExt().(bytes)
//                                    ^^^^^^ reference ujson/JsVisitor#visitExt().(offset)
//                                            ^^^ reference ujson/JsVisitor#visitExt().(len)
//                                                 ^^^^^ reference ujson/JsVisitor#visitExt().(index)
//                                                        ^^^^^^^^^^^^ reference scala/Any#asInstanceOf().
//                                                                     ^ reference ujson/JsVisitor#[T]
    arr.visitEnd(-1)
//  ^^^ reference local5
//      ^^^^^^^^ reference upickle/core/ObjArrVisitor#visitEnd().
  }

  def visitChar(s: Char, index: Int) = visitString(s.toString, index)
//    ^^^^^^^^^ definition ujson/JsVisitor#visitChar(). def visitChar(s: Char, index: Int): J
//              ^ definition ujson/JsVisitor#visitChar().(s) s: Char
//                 ^^^^ reference scala/Char#
//                       ^^^^^ definition ujson/JsVisitor#visitChar().(index) index: Int
//                              ^^^ reference scala/Int#
//                                     ^^^^^^^^^^^ reference upickle/core/Visitor#visitString().
//                                                 ^ reference ujson/JsVisitor#visitChar().(s)
//                                                   ^^^^^^^^ reference scala/Any#toString().
//                                                             ^^^^^ reference ujson/JsVisitor#visitChar().(index)
}
