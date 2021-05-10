# flutter_plugin_helpcrunch

A plugin to allow flutter to connect to helpcrunch

https://github.com/Jerome-Saltmarsh/flutter_helpcrunch_plugin

## Getting Started

``` dart
import 'package:helpcrunch_plugin/helpcrunch_plugin.dart';

await HelpCrunch.initialize(
        organization: 'my organization',
        appId: 0,
        appSecret: 'my secret'
);

await HelpCrunch.showChatScreen();
```


