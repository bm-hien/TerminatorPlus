# TerminatorPlus [BETA]

**This project is on pause indefinitely. Updates are not guaranteed. Please do not use in a production server.**

Additionally, the readme and wiki may be outdated.

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![GitHub](https://img.shields.io/github/languages/code-size/HorseNuggets/TerminatorPlus?color=cyan&label=Size&labelColor=000000&logo=GitHub&style=for-the-badge)
![GitHub](https://img.shields.io/github/license/HorseNuggets/TerminatorPlus?color=violet&logo=GitHub&labelColor=000000&style=for-the-badge)
![Discord](https://img.shields.io/discord/357333217340162069?color=5865F2&label=Discord&logo=Discord&labelColor=23272a&style=for-the-badge)

**TerminatorPlus** is a bukkit (paper) plugin that allows the creation of server-side player bots. Unlike many NPC
plugins that already exist, this project has an emphasis on making the bots as human-like as possible.

### Download

Releases are currently available on our Discord server, which can be found [here](https://discord.gg/vZVSf2D6mz). You
can also find them on the [releases page](https://github.com/HorseNuggets/TerminatorPlus/releases)

### Machine Learning

TerminatorPlus currently utilizes classic population-based reinforcement learning for bot PVP training. Q-learning is a work in progress, along with variable A* pathfinding.

### API Support

Built API artifacts are available on the [releases page](https://github.com/HorseNuggets/TerminatorPlus/releases), and
the Discord server.
See
the [API Module](https://github.com/HorseNuggets/TerminatorPlus/tree/master/TerminatorPlus-API/src/main/java/net/nuggetmc/tplus/api)
for available methods.

Example:
```java
Terminator terminator = TerminatorPlusAPI.getBotManager().createBot(location, "BotName", skin, skinSignature);
```

### Version Support

This plugin is compiled against [Paper 1.21.1](https://papermc.io/downloads/all) but will attempt to load on compatible versions (same major.minor, e.g., 1.21.x). For best results, use the exact version the plugin was compiled for. A warning will be shown if running on a different version.

### Features

- **Ranged Combat**: Bots can use bows to attack targets at range (4-32 blocks) when they have line of sight
- **Auto-Equip**: Bots automatically upgrade their weapons and armor over time (leather → iron → diamond)
- **Underwater Pursuit**: Improved swimming AI allows bots to chase players effectively underwater with faster swim speed and 3D targeting
- **Sprint Chase**: Enhanced chase speed when targets are far away, making it harder for players to escape
- **Block Placing/Breaking**: Intelligent block placement and breaking to navigate obstacles
- **Fall Damage Prevention**: Water bucket MLG and twisting vine clutching
- **Shield Blocking**: Bots can use shields to block attacks

### Future Updates

This project is in a very early stage, and we have many more ideas to tackle.
- [ ] Individual agents assigned per bot
- [ ] A GUI to view currently loaded bots and cool data with them
- [ ] AI data saved to the plugin data folder, able to be loaded into bots
- [ ] Saving config data in memory

## License

This project is licensed under [Eclipse Public License](https://github.com/batchprogrammer314/player-ai/blob/master/LICENSE).
