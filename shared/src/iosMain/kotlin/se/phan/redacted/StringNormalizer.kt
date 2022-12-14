package se.phan.redacted

import platform.Foundation.NSASCIIStringEncoding
import platform.Foundation.NSData
import platform.Foundation.NSString
import platform.Foundation.create
import platform.Foundation.dataUsingEncoding

fun String.normalized(): String {
    return nsdata()?.string() ?: this
}

private fun String.nsdata(): NSData? =
    NSString.create(string = this).dataUsingEncoding(NSASCIIStringEncoding, allowLossyConversion = true)

private fun NSData.string(): String? =
    NSString.create(data = this, encoding = NSASCIIStringEncoding)?.toString()