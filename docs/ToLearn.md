# Renderer für Entitys

Jede Entity hat folgende Klassen
- PathAwayEntity --> die handelt die KI und die pfade die ein ding lang fliegt 
  -  ist main hub für verhalten
  -  wird auf main server seite verwendet (serverseite)
-  Benötigt Mod Initializer --> initialized salles --> darüber müssen die infos gepublisht werden
   - Register über:
```Java
FabricDefaultAttributeRegistry.register(CUBE, CubeEntity.createMobAttributes());
```
- Entity Render
  - Auf Clientseite --> verwaltet wie die Entität angezeigt wird --> enthält Model und Textur
- Client initializer --> erstellt und registriert die entitäten 



https://www.youtube.com/watch?v=wgVnkqqBGFs