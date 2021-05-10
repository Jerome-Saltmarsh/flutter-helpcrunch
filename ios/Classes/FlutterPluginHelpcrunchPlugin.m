#import "FlutterPluginHelpcrunchPlugin.h"
#import <helpcrunch_plugin/helpcrunch_plugin-Swift.h>

@implementation FlutterPluginHelpcrunchPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftFlutterPluginHelpcrunchPlugin registerWithRegistrar:registrar];
}
@end
