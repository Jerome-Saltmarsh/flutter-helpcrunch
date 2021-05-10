package net.bitburst.helpcrunch_plugin

import androidx.annotation.NonNull
import com.helpcrunch.library.core.HelpCrunch
import com.helpcrunch.library.core.models.user.HCUser

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

class FlutterPluginHelpcrunchPlugin : FlutterPlugin, MethodCallHandler {

    private lateinit var channel: MethodChannel

    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "HelpCrunch")
        channel.setMethodCallHandler(this)
    }

    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {

        if (call.method == "logout") {
            HelpCrunch.logout()
            return;
        }

        if (call.method == "initialize") {

            val org: String? = call.argument("organization")

            if (org != null) {

                val appId: Int? = call.argument("appId")

                if (appId != null) {

                    val appSecret: String? = call.argument("appSecret")

                    if (appSecret != null) {

                        var builder = HCUser.Builder()

                        if (call.hasArgument("name")) {
                            val name: String? = call.argument("name")
                            if (name != null) {
                                builder.withName(name);
                            }
                        }
                        if (call.hasArgument("phone")) {
                            val phone: String? = call.argument("phone")
                            if (phone != null) {
                                builder.withPhone(phone);
                            }
                        }
                        if (call.hasArgument("email")) {
                            val email: String? = call.argument("email")
                            if (email != null) {
                                builder.withEmail(email)
                            }
                        }
                        if (call.hasArgument("userId")) {
                            val userId: String? = call.argument("userId")
                            if(userId != null){
                                builder.withUserId(userId)
                            }
                        }
                        if(call.hasArgument("customData")){
                            var customData: Map<String, Any?>? = call.argument("customData")
                            if(customData != null){
                                builder.withCustomData(customData)
                            }
                        }

                        HelpCrunch.initialize(
                                org,
                                appId,
                                appSecret,
                                builder.build()
                        )
                    }
                }
            }
        } else
            if (call.method == "showChatScreen") {
                HelpCrunch.showChatScreen()
            }

        result.success("Hello World")
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }
}
