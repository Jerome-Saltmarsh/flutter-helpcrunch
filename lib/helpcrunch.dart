
import 'dart:async';

import 'package:flutter/services.dart';

class HelpCrunch {

  static const MethodChannel _channel = MethodChannel('HelpCrunch');

  static Future initialize({String organization, int appId, String appSecret, String userId, String email, Map<String, dynamic> customData}) async {
    await _channel.invokeMethod('initialize', {
      'organization': organization,
      'appId': appId,
      'appSecret': appSecret,
      'userId': userId,
      'email': email,
      'customData': customData
    });
  }

  static Future showChatScreen() async {
    await _channel.invokeMethod('showChatScreen');
  }

  static Future logout() async {
    await _channel.invokeMethod('logout');
  }
}
