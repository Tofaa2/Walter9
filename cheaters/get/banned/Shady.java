/*     */ package cheaters.get.banned;
/*     */ import cheaters.get.banned.config.ConfigLogic;
/*     */ import cheaters.get.banned.config.MainCommand;
/*     */ import cheaters.get.banned.config.settings.Setting;
/*     */ import cheaters.get.banned.events.TickEndEvent;
/*     */ import cheaters.get.banned.features.AutoClicker;
/*     */ import cheaters.get.banned.features.AutoGG;
/*     */ import cheaters.get.banned.features.AutoHarp;
/*     */ import cheaters.get.banned.features.AutoSimonSays;
/*     */ import cheaters.get.banned.features.BlockAbilities;
/*     */ import cheaters.get.banned.features.CatGirls;
/*     */ import cheaters.get.banned.features.DisableSwordAnimation;
/*     */ import cheaters.get.banned.features.GemstoneESP;
/*     */ import cheaters.get.banned.features.HideSummons;
/*     */ import cheaters.get.banned.features.ItemKeybind;
/*     */ import cheaters.get.banned.features.MobESP;
/*     */ import cheaters.get.banned.features.RoyalPigeonMacro;
/*     */ import cheaters.get.banned.features.StonklessStonk;
/*     */ import cheaters.get.banned.features.TeleportWithAnything;
/*     */ import cheaters.get.banned.features.dungeonmap.RoomLoader;
/*     */ import cheaters.get.banned.remote.Analytics;
/*     */ import cheaters.get.banned.remote.MayorAPI;
/*     */ import cheaters.get.banned.remote.Updater;
/*     */ import cheaters.get.banned.utils.DungeonUtils;
/*     */ import cheaters.get.banned.utils.KeybindUtils;
/*     */ import cheaters.get.banned.utils.LocationUtils;
/*     */ import cheaters.get.banned.utils.Utils;
/*     */ import java.util.ArrayList;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.settings.KeyBinding;
/*     */ import net.minecraft.command.ICommand;
/*     */ import net.minecraftforge.client.event.ClientChatReceivedEvent;
/*     */ import net.minecraftforge.client.event.GuiOpenEvent;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.fml.client.registry.ClientRegistry;
/*     */ import net.minecraftforge.fml.common.Loader;
/*     */ import net.minecraftforge.fml.common.Mod.EventHandler;
/*     */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*     */ import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
/*     */ import net.minecraftforge.fml.common.eventhandler.EventPriority;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ 
/*     */ @Mod(modid = "autogg", name = "ShadyAddons", version = "4.1.0", clientSideOnly = true, acceptedMinecraftVersions = "[1.8.9]")
/*     */ public class Shady {
/*  48 */   public static final boolean BETA = ("2.1.0-pre2".contains("-pre") || "2.1.0-pre2".equals("@VERSION@")); public static final String MODNAME = "ShadyAddons";
/*     */   public static final String VERSION = "2.1.0-pre2";
/*  50 */   public static final Minecraft mc = Minecraft.func_71410_x();
/*     */   
/*     */   public static boolean usingSkyBlockAddons = false;
/*     */   
/*     */   public static boolean usingPatcher = false;
/*     */   public static boolean usingSkytils = false;
/*  56 */   public static GuiScreen guiToOpen = null;
/*     */   public static boolean enabled = true;
/*     */   private static boolean sentPlayTimeCommand = false;
/*     */   private static boolean sentPlayTimeData = false;
/*  60 */   private static Pattern playTimePattern = Pattern.compile("You have (\\d*) hours and \\d* minutes playtime!");
/*     */   
/*  62 */   public static ArrayList<Setting> settings = ConfigLogic.collect(Config.class);
/*     */   
/*     */   @EventHandler
/*     */   public void preInit(FMLPreInitializationEvent event) {
/*  66 */     ClientCommandHandler.instance.func_71560_a((ICommand)new MainCommand());
/*  67 */     ConfigLogic.load();
/*  68 */     RoomLoader.load();
/*  69 */     Updater.check();
/*  70 */     MayorAPI.fetch();
/*  71 */     Analytics.collect("version", "2.1.0-pre2");
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void init(FMLInitializationEvent event) {
/*  76 */     MinecraftForge.EVENT_BUS.register(new TickEndEvent());
/*  77 */     MinecraftForge.EVENT_BUS.register(this);
/*  78 */     MinecraftForge.EVENT_BUS.register(new Utils());
/*  79 */     MinecraftForge.EVENT_BUS.register(new LocationUtils());
/*  80 */     MinecraftForge.EVENT_BUS.register(new DungeonUtils());
/*  81 */     MinecraftForge.EVENT_BUS.register(new DungeonScanner());
/*     */     
/*  83 */     MinecraftForge.EVENT_BUS.register(new BlockAbilities());
/*  84 */     MinecraftForge.EVENT_BUS.register(new StonklessStonk());
/*  85 */     MinecraftForge.EVENT_BUS.register(new GhostBlocks());
/*  86 */     MinecraftForge.EVENT_BUS.register(new AutoCloseChest());
/*  87 */     MinecraftForge.EVENT_BUS.register(new BossCorleoneFinder());
/*  88 */     MinecraftForge.EVENT_BUS.register(new RoyalPigeonMacro());
/*  89 */     MinecraftForge.EVENT_BUS.register(new AutoGG());
/*  90 */     MinecraftForge.EVENT_BUS.register(new AutoSimonSays());
/*  91 */     MinecraftForge.EVENT_BUS.register(new AbilityKeybind());
/*  92 */     MinecraftForge.EVENT_BUS.register(new AutoClicker());
/*  93 */     MinecraftForge.EVENT_BUS.register(new AutoRenewCrystalHollows());
/*  94 */     MinecraftForge.EVENT_BUS.register(new DisableSwordAnimation());
/*  95 */     MinecraftForge.EVENT_BUS.register(new ShowHiddenEntities());
/*  96 */     MinecraftForge.EVENT_BUS.register(new HideSummons());
/*  97 */     MinecraftForge.EVENT_BUS.register(new TeleportWithAnything());
/*  98 */     MinecraftForge.EVENT_BUS.register(new ItemKeybind());
/*  99 */     MinecraftForge.EVENT_BUS.register(new MobESP());
/* 100 */     MinecraftForge.EVENT_BUS.register(new GemstoneESP());
/* 101 */     MinecraftForge.EVENT_BUS.register(new AutoTerminals());
/* 102 */     MinecraftForge.EVENT_BUS.register(new AutoHarp());
/* 103 */     MinecraftForge.EVENT_BUS.register(new DungeonMap());
/* 104 */     MinecraftForge.EVENT_BUS.register(new CatGirls());
/*     */     
/* 106 */     for (KeyBinding keyBinding : KeybindUtils.keyBindings.values()) {
/* 107 */       ClientRegistry.registerKeyBinding(keyBinding);
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void postInit(FMLPostInitializationEvent event) {
/* 113 */     usingSkyBlockAddons = Loader.isModLoaded("skyblockaddons");
/* 114 */     usingPatcher = Loader.isModLoaded("patcher");
/* 115 */     usingSkytils = Loader.isModLoaded("skytils");
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onTick(TickEndEvent event) {
/* 120 */     if (guiToOpen != null) {
/* 121 */       mc.func_147108_a(guiToOpen);
/* 122 */       guiToOpen = null;
/*     */     } 
/*     */     
/* 125 */     if (Utils.inSkyBlock && !sentPlayTimeCommand) {
/* 126 */       Utils.sendMessageAsPlayer("/playtime");
/* 127 */       sentPlayTimeCommand = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent(priority = EventPriority.HIGHEST)
/*     */   public void onChat(ClientChatReceivedEvent event) {
/* 133 */     if (Utils.inSkyBlock && sentPlayTimeCommand && !sentPlayTimeData && event.message.func_150260_c().contains("minutes playtime!")) {
/* 134 */       Matcher matcher = playTimePattern.matcher(event.message.func_150260_c());
/* 135 */       if (matcher.matches()) {
/* 136 */         Analytics.collect("playtime", matcher.group(1));
/* 137 */         event.setCanceled(true);
/* 138 */         sentPlayTimeData = true;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onGuiOpen(GuiOpenEvent event) {
/* 145 */     if (Updater.shouldUpdate && event.gui instanceof net.minecraft.client.gui.GuiMainMenu) {
/* 146 */       guiToOpen = (GuiScreen)new UpdateGui();
/* 147 */       Updater.shouldUpdate = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void disable() {
/* 152 */     enabled = false;
/* 153 */     for (Setting setting : settings) {
/* 154 */       if (setting instanceof cheaters.get.banned.config.settings.BooleanSetting) setting.set(Boolean.valueOf(false)); 
/* 155 */       if (setting instanceof cheaters.get.banned.config.settings.SelectSetting) setting.set(Integer.valueOf(0)); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Desktop\ShadyAddons-2.1.0-pre2.jar!\cheaters\get\banned\Shady.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */