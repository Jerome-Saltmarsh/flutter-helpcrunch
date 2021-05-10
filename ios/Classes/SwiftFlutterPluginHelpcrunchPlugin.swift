import Flutter
import UIKit
import HelpCrunchSDK

public class SwiftFlutterPluginHelpcrunchPlugin: NSObject, FlutterPlugin {

  public static func register(with registrar: FlutterPluginRegistrar) {

    let channel = FlutterMethodChannel(name: "HelpCrunch", binaryMessenger: registrar.messenger())
    let instance = SwiftFlutterPluginHelpcrunchPlugin()
    registrar.addMethodCallDelegate(instance, channel: channel)
  }
    
    public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult){
        switch call.method {
        case "initialize":
            
            guard let args = call.arguments as? [String: Any] else {
                return
            }
            
            print(args)
            
            let configuration = HCSConfiguration(forOrganization: args["organization"] as! String,
                                                 applicationId: String(args["appId"] as! Int),
                                                 applicationSecret: args["appSecret"] as! String)
            
            let user = HCSUser()
            user.userId = args["userId"] as? String
            user.email = args["email"] as? String
            
            if !(args["customData"] is NSNull) {
                user.customData = args["customData"] as! [String: Any]
            }
            
            HelpCrunch.initWith(configuration, user: user) { (error) in
                HelpCrunch.update(user)
                result("HelpCrunch initialized")
            }
        case "showChatScreen":
            let viewController: UIViewController =
                        (UIApplication.shared.delegate?.window??.rootViewController)!;
            
            HelpCrunch.show(from: viewController) { (error) in
                result("HelpCrunch showed")
            }
        case "logout":
            HelpCrunch.logout()
            result("HelpCrunch logout")
        default:
            print("No Method found")
        }
    }
}
