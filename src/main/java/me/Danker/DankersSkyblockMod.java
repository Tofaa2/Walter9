package me.Danker;

import me.Danker.commands.*;
import me.Danker.events.ChestSlotClickedEvent;
import me.Danker.events.GuiChestBackgroundDrawnEvent;
import me.Danker.events.RenderOverlay;
import me.Danker.features.*;
import me.Danker.features.loot.LootDisplay;
import me.Danker.features.loot.LootTracker;
import me.Danker.features.puzzlesolvers.*;
import me.Danker.gui.*;
import me.Danker.handlers.ConfigHandler;
import me.Danker.handlers.PacketHandler;
import me.Danker.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.command.ICommand;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.ClickEvent.Action;
import net.minecraft.event.HoverEvent;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.*;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;
import net.minecraftforge.fml.common.versioning.DefaultArtifactVersion;
import org.apache.commons.lang3.time.StopWatch;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Mod(modid = DankersSkyblockMod.MODID, version = DankersSkyblockMod.VERSION, clientSideOnly = true)
public class DankersSkyblockMod {
    public static final String MODID = "Danker's Skyblock Mod";
    public static final String VERSION = "1.8.6";
    public static int titleTimer = -1;
    public static boolean showTitle = false;
    public static String titleText = "";
    public static int tickAmount = 1;
    public static KeyBinding[] keyBindings = new KeyBinding[3];
    public static boolean usingLabymod = false;
    public static boolean usingOAM = false;
    static boolean OAMWarning = false;
    public static String guiToOpen = null;
    static boolean foundLivid = false;
    static Entity livid = null;
    public static double cakeTime;
    public static double nextBonzoUse = 0;
    public static boolean firstLaunch = false;
    public static String configDirectory;

    public static String MAIN_COLOUR;
    public static String SECONDARY_COLOUR;
    public static String ERROR_COLOUR;
    public static String DELIMITER_COLOUR;
    public static String TYPE_COLOUR;
    public static String VALUE_COLOUR;
    public static String SKILL_AVERAGE_COLOUR;
    public static String ANSWER_COLOUR;
    public static String SKILL_50_COLOUR;
    public static String COORDS_COLOUR;
    public static String CAKE_COLOUR;
    public static String SKILL_TRACKER_COLOUR;
    public static String TRIVIA_WRONG_ANSWER_COLOUR;
    public static String BONZO_COLOR;
    public static int LOWEST_BLAZE_COLOUR;
    public static int HIGHEST_BLAZE_COLOUR;
    public static int PET_1_TO_9;
    public static int PET_10_TO_19;
    public static int PET_20_TO_29;
    public static int PET_30_TO_39;
    public static int PET_40_TO_49;
    public static int PET_50_TO_59;
    public static int PET_60_TO_69;
    public static int PET_70_TO_79;
    public static int PET_80_TO_89;
    public static int PET_90_TO_99;
    public static int PET_100;

    @EventHandler
    public void init(FMLInitializationEvent event) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new ArachneESP());
        MinecraftForge.EVENT_BUS.register(new AutoDisplay());
        MinecraftForge.EVENT_BUS.register(new AutoSwapToPickBlock());
        MinecraftForge.EVENT_BUS.register(new BlazeSolver());
        MinecraftForge.EVENT_BUS.register(new BonzoMaskTimer());
        MinecraftForge.EVENT_BUS.register(new BoulderSolver());
        MinecraftForge.EVENT_BUS.register(new CakeTimer());
        MinecraftForge.EVENT_BUS.register(new ChronomatronSolver());
        MinecraftForge.EVENT_BUS.register(new ClickInOrderSolver());
        MinecraftForge.EVENT_BUS.register(new CreeperSolver());
        MinecraftForge.EVENT_BUS.register(new CustomMusic());
        MinecraftForge.EVENT_BUS.register(new DungeonTimer());
        MinecraftForge.EVENT_BUS.register(new ExpertiseLore());
        MinecraftForge.EVENT_BUS.register(new FasterMaddoxCalling());
        MinecraftForge.EVENT_BUS.register(new GoldenEnchants());
        MinecraftForge.EVENT_BUS.register(new GolemSpawningAlert());
        MinecraftForge.EVENT_BUS.register(new GpartyNotifications());
        MinecraftForge.EVENT_BUS.register(new HideTooltipsInExperiments());
        MinecraftForge.EVENT_BUS.register(new IceWalkSolver());
        MinecraftForge.EVENT_BUS.register(new LividSolver());
        MinecraftForge.EVENT_BUS.register(new LootDisplay());
        MinecraftForge.EVENT_BUS.register(new LootTracker());
        MinecraftForge.EVENT_BUS.register(new LowHealthNotifications());
        MinecraftForge.EVENT_BUS.register(new NecronNotifications());
        MinecraftForge.EVENT_BUS.register(new NoF3Coords());
        MinecraftForge.EVENT_BUS.register(new NotifySlayerSlain());
        MinecraftForge.EVENT_BUS.register(new PetColours());
        MinecraftForge.EVENT_BUS.register(new Reparty());
        MinecraftForge.EVENT_BUS.register(new SelectAllColourSolver());
        MinecraftForge.EVENT_BUS.register(new SilverfishSolver());
        MinecraftForge.EVENT_BUS.register(new Skill50Display());
        MinecraftForge.EVENT_BUS.register(new SkillTracker());
        MinecraftForge.EVENT_BUS.register(new SlayerESP());
        MinecraftForge.EVENT_BUS.register(new SpamHider());
        MinecraftForge.EVENT_BUS.register(new SpiritBearAlert());
        MinecraftForge.EVENT_BUS.register(new StartsWithSolver());
        MinecraftForge.EVENT_BUS.register(new StopSalvagingStarredItems());
        MinecraftForge.EVENT_BUS.register(new SuperpairsSolver());
        MinecraftForge.EVENT_BUS.register(new ThreeManSolver());
        MinecraftForge.EVENT_BUS.register(new TicTacToeSolver());
        MinecraftForge.EVENT_BUS.register(new TriviaSolver());
        MinecraftForge.EVENT_BUS.register(new UltrasequencerSolver());
        MinecraftForge.EVENT_BUS.register(new UpdateChecker());
        MinecraftForge.EVENT_BUS.register(new WatcherReadyAlert());
        MinecraftForge.EVENT_BUS.register(new WaterSolver());

        ConfigHandler.reloadConfig();
        GoldenEnchants.init();
        TriviaSolver.init();
        CustomMusic.init(configDirectory);

        keyBindings[0] = new KeyBinding("Open Maddox Menu", Keyboard.KEY_M, "Danker's Skyblock Mod");
        keyBindings[1] = new KeyBinding("Regular Ability", Keyboard.KEY_NUMPAD4, "Danker's Skyblock Mod");
        keyBindings[2] = new KeyBinding("Start/Stop Skill Tracker", Keyboard.KEY_NUMPAD5, "Danker's Skyblock Mod");
        keyBindings[3] = new KeyBinding("Bone Macro", Keyboard.KEY_B, "Danker's Skyblock Mod");
        keyBindings[4] = new KeyBinding("Reset Fake ID", Keyboard.KEY_P, "Danker's Skyblock Mod");
        keyBindings[5] = new KeyBinding("Right Click Spam", Keyboard.KEY_X, "Danker's Skyblock Mod");
        keyBindings[6] = new KeyBinding("Hyperion Bind", Keyboard.KEY_I, "Danker's Skyblock Mod");
        keyBindings[7] = new KeyBinding("Ghost Block Bind", Keyboard.KEY_G, "Danker's Skyblock Mod");

        for (KeyBinding keyBinding : keyBindings) {
            ClientRegistry.registerKeyBinding(keyBinding);
        }
    }

    @EventHandler
    public void preInit(final FMLPreInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(new ArmourCommand());
        ClientCommandHandler.instance.registerCommand(new BankCommand());
        ClientCommandHandler.instance.registerCommand(new CustomMusicCommand());
        ClientCommandHandler.instance.registerCommand(new DHelpCommand());
        ClientCommandHandler.instance.registerCommand(new DankerGuiCommand());
        ClientCommandHandler.instance.registerCommand(new DisplayCommand());
        ClientCommandHandler.instance.registerCommand(new DungeonsCommand());
        ClientCommandHandler.instance.registerCommand(new FairySoulsCommand());
        ClientCommandHandler.instance.registerCommand(new GetkeyCommand());
        ClientCommandHandler.instance.registerCommand(new GuildOfCommand());
        ClientCommandHandler.instance.registerCommand(new ImportFishingCommand());
        ClientCommandHandler.instance.registerCommand(new LobbyBankCommand());
        ClientCommandHandler.instance.registerCommand(new LobbySkillsCommand());
        ClientCommandHandler.instance.registerCommand(new LootCommand());
        ClientCommandHandler.instance.registerCommand(new MoveCommand());
        ClientCommandHandler.instance.registerCommand(new PetsCommand());
        ClientCommandHandler.instance.registerCommand(new ReloadConfigCommand());
        ClientCommandHandler.instance.registerCommand(new ResetLootCommand());
        ClientCommandHandler.instance.registerCommand(new ScaleCommand());
        ClientCommandHandler.instance.registerCommand(new SetkeyCommand());
        ClientCommandHandler.instance.registerCommand(new SkillTrackerCommand());
        ClientCommandHandler.instance.registerCommand(new SkillsCommand());
        ClientCommandHandler.instance.registerCommand(new SkyblockPlayersCommand());
        ClientCommandHandler.instance.registerCommand(new SlayerCommand());
        ClientCommandHandler.instance.registerCommand(new ToggleCommand());

        configDirectory = event.getModConfigurationDirectory().toString();
    }

    @EventHandler
    public void postInit(final FMLPostInitializationEvent event) {
		Package[] packages = Package.getPackages();
		for (Package p : packages){
			if (p.getName().startsWith("com.spiderfrog.gadgets") || p.getName().startsWith("com.spiderfrog.oldanimations")){
				usingOAM = true;
				break;
			}
		}
		System.out.println("OAM detection: " + usingOAM);

    	usingLabymod = Loader.isModLoaded("labymod");
    	System.out.println("LabyMod detection: " + usingLabymod);

        if (!ClientCommandHandler.instance.getCommands().containsKey("reparty")) {
            ClientCommandHandler.instance.registerCommand(new RepartyCommand());
        } else if (ConfigHandler.getBoolean("commands", "reparty")) {
            for (Map.Entry<String, ICommand> entry : ClientCommandHandler.instance.getCommands().entrySet()) {
                if (entry.getKey().equals("reparty") || entry.getKey().equals("rp")) {
                    entry.setValue(new RepartyCommand());
                }
            }
        }

    }

    @SubscribeEvent
	public void onGuiOpenEvent(GuiOpenEvent event) {
		if (event.gui instanceof GuiMainMenu && usingOAM && !OAMWarning) {
		    event.gui = new WarningGuiRedirect(new WarningGui());
		    OAMWarning = true;
		}
	}

    @SubscribeEvent
    public void onJoin(EntityJoinWorldEvent event) {
        if (firstLaunch) {
            firstLaunch = false;
            ConfigHandler.writeBooleanConfig("misc", "firstLaunch", false);

            IChatComponent chatComponent = new ChatComponentText(
                    EnumChatFormatting.GOLD + "Thank you for downloading Danker's Skyblock Mod.\n" +
                         "To get started, run the command " + EnumChatFormatting.GOLD + "/dsm" + EnumChatFormatting.RESET + " to view all the mod features."
            );
            chatComponent.setChatStyle(chatComponent.getChatStyle().setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText("Click to open the DSM menu."))).setChatClickEvent(new ClickEvent(Action.RUN_COMMAND, "/dsm")));

            new Thread(() -> {
                while (true) {
                    if (Minecraft.getMinecraft().thePlayer == null) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        continue;
                    }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Minecraft.getMinecraft().thePlayer.addChatMessage(chatComponent);
                    break;
                }
            }).start();
        }
    }

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
    	String message = StringUtils.stripControlCodes(event.message.getUnformattedText());

        if (message.startsWith("Your new API key is ") && Utils.isOnHypixel()) {
            String apiKey = event.message.getSiblings().get(0).getChatStyle().getChatClickEvent().getValue();
            ConfigHandler.writeStringConfig("api", "APIKey", apiKey);
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Set API key to " + DankersSkyblockMod.SECONDARY_COLOUR + apiKey));
        }

		// Reparty command
        // Getting party
        if (RepartyCommand.gettingParty) {
            if (message.contains("-----")) {
                switch(RepartyCommand.Delimiter) {
                    case 0:
                        System.out.println("Get Party Delimiter Cancelled");
                        RepartyCommand.Delimiter++;
                        event.setCanceled(true);
                        return;
                    case 1:
                        System.out.println("Done querying party");
                        RepartyCommand.gettingParty = false;
                        RepartyCommand.Delimiter = 0;
                        event.setCanceled(true);
                        return;
                }
            }else if (message.startsWith("Party M") || message.startsWith("Party Leader")){
                EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;

                Pattern party_start_pattern = Pattern.compile("^Party Members \\((\\d+)\\)$");
                Pattern leader_pattern = Pattern.compile("^Party Leader: (?:\\[.+?] )?(\\w+) ●$");
                Pattern members_pattern = Pattern.compile(" (?:\\[.+?] )?(\\w+) ●");
                Matcher party_start = party_start_pattern.matcher(message);
                Matcher leader = leader_pattern.matcher(message);
                Matcher members = members_pattern.matcher(message);

                if (party_start.matches() && Integer.parseInt(party_start.group(1)) == 1) {
                    player.addChatMessage(new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "You cannot reparty yourself."));
                    RepartyCommand.partyThread.interrupt();
                } else if (leader.matches() && !(leader.group(1).equals(player.getName()))) {
                    player.addChatMessage(new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "You are not party leader."));
                    RepartyCommand.partyThread.interrupt();
                } else {
                    while (members.find()) {
                        String partyMember = members.group(1);
                        if (!partyMember.equals(player.getName())) {
                            RepartyCommand.party.add(partyMember);
                            System.out.println(partyMember);
                        }
                    }
                }
                event.setCanceled(true);
                return;
            }
        }
        // Disbanding party
        if (RepartyCommand.disbanding) {
            if (message.contains("-----")) {
                switch (RepartyCommand.Delimiter) {
                    case 0:
                        System.out.println("Disband Delimiter Cancelled");
                        RepartyCommand.Delimiter++;
                        event.setCanceled(true);
                        return;
                    case 1:
                        System.out.println("Done disbanding");
                        RepartyCommand.disbanding = false;
                        RepartyCommand.Delimiter = 0;
                        event.setCanceled(true);
                        return;
                }
            } else if (message.endsWith("has disbanded the party!")) {
                event.setCanceled(true);
                return;
            }
        }
        // Inviting
        if (RepartyCommand.inviting) {
            if (message.contains("-----")) {
                switch (RepartyCommand.Delimiter) {
                    case 1:
                        event.setCanceled(true);
                        RepartyCommand.Delimiter = 0;
                        System.out.println("Player Invited!");
                        RepartyCommand.inviting = false;
                        return;
                    case 0:
                        RepartyCommand.Delimiter++;
                        event.setCanceled(true);
                        return;
                }
            } else if (message.endsWith(" to the party! They have 60 seconds to accept.")) {
                Pattern invitePattern = Pattern.compile("(?:(?:\\[.+?] )?(?:\\w+) invited )(?:\\[.+?] )?(\\w+)");
                Matcher invitee = invitePattern.matcher(message);
                if (invitee.find()) {
                    System.out.println("" + invitee.group(1) + ": " + RepartyCommand.repartyFailList.remove(invitee.group(1)));
                }
                event.setCanceled(true);
                return;
            } else if (message.contains("Couldn't find a player") || message.contains("You cannot invite that player")) {
                event.setCanceled(true);
                return;
            }
        }
        // Fail Inviting
        if (RepartyCommand.failInviting) {
            if (message.contains("-----")) {
                switch (RepartyCommand.Delimiter) {
                    case 1:
                        event.setCanceled(true);
                        RepartyCommand.Delimiter = 0;
                        System.out.println("Player Invited!");
                        RepartyCommand.inviting = false;
                        return;
                    case 0:
                        RepartyCommand.Delimiter++;
                        event.setCanceled(true);
                        return;
                }
            } else if (message.endsWith(" to the party! They have 60 seconds to accept.")) {
                Pattern invitePattern = Pattern.compile("(?:(?:\\[.+?] )?(?:\\w+) invited )(?:\\[.+?] )?(\\w+)");
                Matcher invitee = invitePattern.matcher(message);
                if (invitee.find()) {
                    System.out.println("" + invitee.group(1) + ": " + RepartyCommand.repartyFailList.remove(invitee.group(1)));
                }
                event.setCanceled(true);
                return;
            } else if (message.contains("Couldn't find a player") || message.contains("You cannot invite that player")) {
                event.setCanceled(true);
                return;
            }
        }

    	if (!Utils.inSkyblock) return;

    	// Action Bar
    	if (event.type == 2) {
			EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
			String[] actionBarSections = event.message.getUnformattedText().split(" {3,}");

			for (String section : actionBarSections) {
    			if (section.contains("+") && section.contains("/") && section.contains("(")) {
    				if (!section.contains("Runecrafting") && !section.contains("Carpentry")) {
						if (ToggleCommand.autoSkillTrackerToggled && System.currentTimeMillis() / 1000 - timeSinceGained <= 2) {
							if (skillStopwatch.isStarted() && skillStopwatch.isSuspended()) {
								skillStopwatch.resume();
							} else if (!skillStopwatch.isStarted()) {
								skillStopwatch.start();
							}
						}
						timeSinceGained = System.currentTimeMillis() / 1000;

						int limit = section.contains("Farming") || section.contains("Enchanting") ? 60 : 50;
						double currentXP = Double.parseDouble(section.substring(section.indexOf("(") + 1, section.indexOf("/")).replace(",", ""));
    					int xpToLevelUp = Integer.parseInt(section.substring(section.indexOf("/") + 1, section.indexOf(")")).replaceAll(",", ""));
    					xpLeft = xpToLevelUp - currentXP;
    					int previousXP = Utils.getPastXpEarned(xpToLevelUp, limit);
						double totalXP = currentXP + previousXP;

    					String skill = section.substring(section.indexOf(" ") + 1, section.lastIndexOf(" "));
    					switch (skill) {
	    					case "Farming":
	    						lastSkill = "Farming";
	    						if (farmingXP != 0) {
	    							if (skillStopwatch.isStarted() && !skillStopwatch.isSuspended()) farmingXPGained += totalXP - farmingXP;
	    						}
								farmingXP = totalXP;
	    						break;
	    					case "Mining":
	    						lastSkill = "Mining";
	    						if (miningXP != 0) {
	    							if (skillStopwatch.isStarted() && !skillStopwatch.isSuspended()) miningXPGained += totalXP - miningXP;
	    						}
								miningXP = totalXP;
	    						break;
	    					case "Combat":
	    						lastSkill = "Combat";
	    						if (combatXP != 0) {
	    							if (skillStopwatch.isStarted() && !skillStopwatch.isSuspended()) combatXPGained += totalXP - combatXP;
	    						}
								combatXP = totalXP;
	    						break;
	    					case "Foraging":
	    						lastSkill = "Foraging";
	    						if (foragingXP != 0) {
	    							if (skillStopwatch.isStarted() && !skillStopwatch.isSuspended()) foragingXPGained += totalXP - foragingXP;
	    						}
								foragingXP = totalXP;
	    						break;
	    					case "Fishing":
	    						lastSkill = "Fishing";
	    						if (fishingXP != 0) {
	    							if (skillStopwatch.isStarted() && !skillStopwatch.isSuspended()) fishingXPGained += totalXP - fishingXP;
	    						}
								fishingXP = totalXP;
	    						break;
	    					case "Enchanting":
	    						lastSkill = "Enchanting";
	    						if (enchantingXP != 0) {
	    							if (skillStopwatch.isStarted() && !skillStopwatch.isSuspended()) enchantingXPGained += totalXP - enchantingXP;
	    						}
								enchantingXP = totalXP;
	    						break;
	    					case "Alchemy":
	    						lastSkill = "Alchemy";
	    						if (alchemyXP != 0) {
	    							if (skillStopwatch.isStarted() && !skillStopwatch.isSuspended()) alchemyXPGained += totalXP - alchemyXP;
	    						}
								alchemyXP = totalXP;
	    						break;
	    					default:
	    						System.err.println("Unknown skill.");
    					}
    				}

    				if (ToggleCommand.skill50DisplayToggled && !section.contains("Runecrafting")) {
    					String xpGained = section.substring(section.indexOf("+"), section.indexOf("(") - 1);
    					double currentXp = Double.parseDouble(section.substring(section.indexOf("(") + 1, section.indexOf("/")).replace(",", ""));
    					int limit;
    					int totalXp;
    					if (section.contains("Farming") || section.contains("Enchanting")) {
    						limit = 60;
    						totalXp = 111672425;
    					} else {
    						limit = 50;
    						totalXp = 55172425;
    					}
    					int previousXp = Utils.getPastXpEarned(Integer.parseInt(section.substring(section.indexOf("/") + 1, section.indexOf(")")).replaceAll(",", "")), limit);
    					double percentage = Math.floor(((currentXp + previousXp) / totalXp) * 10000D) / 100D;

    					NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
    					skillTimer = SKILL_TIME;
    					showSkill = true;
    					skillText = SKILL_50_COLOUR + xpGained + " (" + nf.format(currentXp + previousXp) + "/" + nf.format(totalXp) + ") " + percentage + "%";
    				}
    			}
    		}
    		return;
    	}

        if (ToggleCommand.bonzoTimerToggled && Utils.inDungeons && message.contains("Bonzo's Mask") && message.contains("saved your life!")) {
            double usedTime = System.currentTimeMillis() / 1000;
            Minecraft mc = Minecraft.getMinecraft();
            EntityPlayerSP player = mc.thePlayer;
            ItemStack bonzoMask = player.getCurrentArmor(3);
            if (bonzoMask != null && bonzoMask.getItem() == Items.skull) {
                int cooldownSeconds = 0;
                for (String line : Utils.getItemLore(bonzoMask)) {
                    String stripped = StringUtils.stripControlCodes(line);
                    if (stripped.startsWith("Cooldown: "))
                        cooldownSeconds = Integer.parseInt(stripped.replaceAll("[^\\d]", ""));
                }
                System.out.println("Parsed Bonzo Mask Cooldown: " + cooldownSeconds);
                if (cooldownSeconds > 0)
                    nextBonzoUse = usedTime + cooldownSeconds;
            }
        }

        // Dungeon chat spoken by an NPC, containing :
        if (ToggleCommand.threeManToggled && Utils.inDungeons && message.contains("[NPC]")) {
            for (String solution : riddleSolutions) {
                if (message.contains(solution)) {
                    String npcName = message.substring(message.indexOf("]") + 2, message.indexOf(":"));
                    Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(ANSWER_COLOUR + EnumChatFormatting.BOLD + StringUtils.stripControlCodes(npcName) + MAIN_COLOUR + " has the blessing."));
                    break;
                }
            }
        }

        if (ToggleCommand.necronNotificationsToggled && Utils.inDungeons && message.contains("[BOSS] Necron:")) {
            Minecraft mc = Minecraft.getMinecraft();
            World world = mc.theWorld;
            if (message.contains("You tricked me!") || message.contains("That beam, it hurts! IT HURTS!!")) {
                Utils.createTitle(EnumChatFormatting.RED + "NECRON STUCK!", 2);
            } else if (message.contains("STOP USING MY FACTORY AGAINST ME!") || message.contains("OOF") || message.contains("ANOTHER TRAP!! YOUR TRICKS ARE FUTILE!") || message.contains("SERIOUSLY? AGAIN?!") || message.contains("STOP!!!!!")) {
                List<EntityArmorStand> necronLabels = world.getEntities(EntityArmorStand.class, (entity -> {
                    if (!entity.hasCustomName()) return false;
                    if (!entity.getCustomNameTag().contains("Necron")) return false;
                    return true;
                }));
                if (necronLabels.size() == 0) {
                    Utils.createTitle(EnumChatFormatting.WHITE + "NECRON STUNNED!", 2);
                } else {
                    EntityArmorStand necron = necronLabels.get(0);
                    double x = necron.posX;
                    double z = necron.posZ;

                    BlockPos blockPos = new BlockPos(x, 168, z);

                    IBlockState blockState = world.getBlockState(blockPos);
                    Block block = blockState.getBlock();

                    if (block != Blocks.stained_hardened_clay) {
                        Utils.createTitle(EnumChatFormatting.WHITE + "NECRON STUNNED!", 2);
                    } else {
                        switch (block.getDamageValue(world, blockPos)) {
                            case 4:
                                Utils.createTitle(EnumChatFormatting.YELLOW + "YELLOW PILLAR!", 2);
                                break;
                            case 5:
                                Utils.createTitle(EnumChatFormatting.GREEN + "LIME PILLAR!", 2);
                                break;
                            case 11:
                                Utils.createTitle(EnumChatFormatting.BLUE + "BLUE PILLAR!", 2);
                                break;
                            default:
                                Utils.createTitle(EnumChatFormatting.WHITE + "NECRON STUNNED!", 2);
                        }
                    }

                }
            } else if (message.contains("I'VE HAD ENOUGH! YOU'RE NOT HITTING ME WITH ANY MORE PILLARS!")) {
                Utils.createTitle(EnumChatFormatting.RED + "RED PILLAR!", 2);
            } else if (message.contains("ARGH!")) {
                Utils.createTitle(EnumChatFormatting.RED + "EXPLOSION OVER!", 2);
            }
        }

        if (message.contains("[BOSS] The Watcher: You have proven yourself. You may pass.")) {
            watcherClearTime = System.currentTimeMillis() / 1000;
        }
        if (message.contains("[BOSS] The Watcher: That will be enough for now.")) {
            if (ToggleCommand.watcherReadyToggled) Utils.createTitle(EnumChatFormatting.RED + "WATCHER READY", 2);
        }
        if (message.contains("PUZZLE FAIL! ") || message.contains("chose the wrong answer! I shall never forget this moment")) {
            puzzleFails++;
        }

        if (message.contains(":")) return;

        // Spirit Sceptre
        if (!ToggleCommand.sceptreMessages && message.contains("Your Spirit Sceptre hit ")) {
            event.setCanceled(true);
            return;
        }
        // Midas Staff
        if (!ToggleCommand.midasStaffMessages && message.contains("Your Molten Wave hit ")) {
            event.setCanceled(true);
            return;
        }
        // Heals
        if (!ToggleCommand.healMessages && message.contains(" health!") && (message.contains("You healed ") || message.contains(" healed you for "))) {
            event.setCanceled(true);
            return;
        }
        // Ability Cooldown
        if (!ToggleCommand.cooldownMessages && message.contains("This ability is on cooldown for ")) {
          event.setCanceled(true);
          return;
        }
        // Out of mana messages
        if (!ToggleCommand.manaMessages && message.contains("You do not have enough mana to do this!")) {
          event.setCanceled(true);
          return;
        }
        // Implosion
        if (!ToggleCommand.implosionMessages) {
            if (message.contains("Your Implosion hit ") || message.contains("There are blocks in the way")) {
                event.setCanceled(true);
                return;
            }
        }

        if (ToggleCommand.oruoToggled && Utils.inDungeons) {
        	if (message.contains("What SkyBlock year is it?")) {
                double currentTime = System.currentTimeMillis() /1000L;

                double diff = Math.floor(currentTime - 1560276000);

                int year = (int) (diff / 446400 + 1);
                triviaAnswers = new String[]{"Year " + year};
            } else {
                for (String question : triviaSolutions.keySet()) {
                    if (message.contains(question)) {
                        triviaAnswers = triviaSolutions.get(question);
                        break;
                    }
                }
            }

        	// Set wrong answers to red and remove click events
        	if (triviaAnswers != null && (message.contains("ⓐ") || message.contains("ⓑ") || message.contains("ⓒ"))) {
        		boolean isSolution = false;
        		for (String solution : triviaAnswers) {
        			if (message.contains(solution)) {
        				isSolution = true;
        				break;
					}
        		}
        		if (!isSolution) {
        			char letter = message.charAt(5);
        			String option = message.substring(6);
        			event.message = new ChatComponentText("     " + EnumChatFormatting.GOLD + letter + TRIVIA_WRONG_ANSWER_COLOUR + option);
        			return;
        		}
        	}
        }

		if (ToggleCommand.gpartyToggled) {
			if (message.contains(" has invited all members of ")) {
				try {
					final SystemTray tray = SystemTray.getSystemTray();
					final Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
					final TrayIcon trayIcon = new TrayIcon(image, "Guild Party Notifier");
					trayIcon.setImageAutoSize(true);
					trayIcon.setToolTip("Guild Party Notifier");
					tray.add(trayIcon);
					trayIcon.displayMessage("Guild Party", message, TrayIcon.MessageType.INFO);
					tray.remove(trayIcon);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}

		if (ToggleCommand.golemAlertToggled) {
			if (message.contains("The ground begins to shake as an Endstone Protector rises from below!")) {
				Utils.createTitle(EnumChatFormatting.RED + "GOLEM SPAWNING!", 3);
			}
		}

		if (message.contains("Yum! You gain +") && message.contains(" for 48 hours!")) {
			cakeTime = System.currentTimeMillis() / 1000 + 172800; // Add 48 hours
			ConfigHandler.writeDoubleConfig("misc", "cakeTime", cakeTime);
		}

		boolean wolfRNG = false;
		boolean spiderRNG = false;
		boolean zombieRNG = false;
		// T6 books
		if (message.contains("VERY RARE DROP!  (Enchanted Book)") || message.contains("CRAZY RARE DROP!  (Enchanted Book)")) {
			// Loop through scoreboard to see what boss you're doing
			List<String> scoreboard = ScoreboardHandler.getSidebarLines();
			for (String s : scoreboard) {
				String sCleaned = ScoreboardHandler.cleanSB(s);
				if (sCleaned.contains("Sven Packmaster")) {
					LootCommand.wolfBooks++;
					ConfigHandler.writeIntConfig("wolf", "book", LootCommand.wolfBooks);
				} else if (sCleaned.contains("Tarantula Broodfather")) {
					LootCommand.spiderBooks++;
					ConfigHandler.writeIntConfig("spider", "book", LootCommand.spiderBooks);
				} else if (sCleaned.contains("Revenant Horror")) {
					LootCommand.zombieBooks++;
					ConfigHandler.writeIntConfig("zombie", "book", LootCommand.zombieBooks);
				}
			}
		}

		// Wolf
		if (message.contains("Talk to Maddox to claim your Wolf Slayer XP!")) {
			LootCommand.wolfSvens++;
			LootCommand.wolfSvensSession++;
			if (LootCommand.wolfBosses != -1) {
				LootCommand.wolfBosses++;
			}
			if (LootCommand.wolfBossesSession != -1) {
				LootCommand.wolfBossesSession++;
			}
			ConfigHandler.writeIntConfig("wolf", "svens", LootCommand.wolfSvens);
			ConfigHandler.writeIntConfig("wolf", "bossRNG", LootCommand.wolfBosses);
		} else if (message.contains("RARE DROP! (Hamster Wheel)")) {
			LootCommand.wolfWheelsDrops++;
			LootCommand.wolfWheelsDropsSession++;
			ConfigHandler.writeIntConfig("wolf", "wheelDrops", LootCommand.wolfWheelsDrops);
		} else if (message.contains("VERY RARE DROP!  (") && message.contains(" Spirit Rune I)")) { // Removing the unicode here *should* fix rune drops not counting
			LootCommand.wolfSpirits++;
			LootCommand.wolfSpiritsSession++;
			ConfigHandler.writeIntConfig("wolf", "spirit", LootCommand.wolfSpirits);
		} else if (message.contains("CRAZY RARE DROP!  (Red Claw Egg)")) {
			wolfRNG = true;
			LootCommand.wolfEggs++;
			LootCommand.wolfEggsSession++;
			ConfigHandler.writeIntConfig("wolf", "egg", LootCommand.wolfEggs);
			if (ToggleCommand.rngesusAlerts) Utils.createTitle(EnumChatFormatting.DARK_RED + "RED CLAW EGG!", 3);
		} else if (message.contains("CRAZY RARE DROP!  (") && message.contains(" Couture Rune I)")) {
			wolfRNG = true;
			LootCommand.wolfCoutures++;
			LootCommand.wolfCouturesSession++;
			ConfigHandler.writeIntConfig("wolf", "couture", LootCommand.wolfCoutures);
			if (ToggleCommand.rngesusAlerts) Utils.createTitle(EnumChatFormatting.GOLD + "COUTURE RUNE!", 3);
		} else if (message.contains("CRAZY RARE DROP!  (Grizzly Bait)") || message.contains("CRAZY RARE DROP! (Rename Me)")) { // How did Skyblock devs even manage to make this item Rename Me
			wolfRNG = true;
			LootCommand.wolfBaits++;
			LootCommand.wolfBaitsSession++;
			ConfigHandler.writeIntConfig("wolf", "bait", LootCommand.wolfBaits);
			if (ToggleCommand.rngesusAlerts) Utils.createTitle(EnumChatFormatting.AQUA + "GRIZZLY BAIT!", 3);
		} else if (message.contains("CRAZY RARE DROP!  (Overflux Capacitor)")) {
			wolfRNG = true;
			LootCommand.wolfFluxes++;
			LootCommand.wolfFluxesSession++;
			ConfigHandler.writeIntConfig("wolf", "flux", LootCommand.wolfFluxes);
			if (ToggleCommand.rngesusAlerts) Utils.createTitle(EnumChatFormatting.DARK_PURPLE + "OVERFLUX CAPACITOR!", 5);
		} else if (message.contains("Talk to Maddox to claim your Spider Slayer XP!")) { // Spider
			LootCommand.spiderTarantulas++;
			LootCommand.spiderTarantulasSession++;
			if (LootCommand.spiderBosses != -1) {
				LootCommand.spiderBosses++;
			}
			if (LootCommand.spiderBossesSession != -1) {
				LootCommand.spiderBossesSession++;
			}
			ConfigHandler.writeIntConfig("spider", "tarantulas", LootCommand.spiderTarantulas);
			ConfigHandler.writeIntConfig("spider", "bossRNG", LootCommand.spiderBosses);
		} else if (message.contains("RARE DROP! (Toxic Arrow Poison)")) {
			LootCommand.spiderTAPDrops++;
			LootCommand.spiderTAPDropsSession++;
			ConfigHandler.writeIntConfig("spider", "tapDrops", LootCommand.spiderTAPDrops);
		} else if (message.contains("VERY RARE DROP!  (") && message.contains(" Bite Rune I)")) {
			LootCommand.spiderBites++;
			LootCommand.spiderBitesSession++;
			ConfigHandler.writeIntConfig("spider", "bite", LootCommand.spiderBites);
		} else if (message.contains("VERY RARE DROP!  (Spider Catalyst)")) {
			LootCommand.spiderCatalysts++;
			LootCommand.spiderCatalystsSession++;
			ConfigHandler.writeIntConfig("spider", "catalyst", LootCommand.spiderCatalysts);
		} else if (message.contains("CRAZY RARE DROP!  (Fly Swatter)")) {
			spiderRNG = true;
			LootCommand.spiderSwatters++;
			LootCommand.spiderSwattersSession++;
			ConfigHandler.writeIntConfig("spider", "swatter", LootCommand.spiderSwatters);
			if (ToggleCommand.rngesusAlerts) Utils.createTitle(EnumChatFormatting.LIGHT_PURPLE + "FLY SWATTER!", 3);
		} else if (message.contains("CRAZY RARE DROP!  (Tarantula Talisman")) {
			spiderRNG = true;
			LootCommand.spiderTalismans++;
			LootCommand.spiderTalismansSession++;
			ConfigHandler.writeIntConfig("spider", "talisman", LootCommand.spiderTalismans);
			if (ToggleCommand.rngesusAlerts) Utils.createTitle(EnumChatFormatting.DARK_PURPLE + "TARANTULA TALISMAN!", 3);
		} else if (message.contains("CRAZY RARE DROP!  (Digested Mosquito)")) {
			spiderRNG = true;
			LootCommand.spiderMosquitos++;
			LootCommand.spiderMosquitosSession++;
			ConfigHandler.writeIntConfig("spider", "mosquito", LootCommand.spiderMosquitos);
			if (ToggleCommand.rngesusAlerts) Utils.createTitle(EnumChatFormatting.GOLD + "DIGESTED MOSQUITO!", 5);
		} else if (message.contains("Talk to Maddox to claim your Zombie Slayer XP!")) { // Zombie
			LootCommand.zombieRevs++;
			LootCommand.zombieRevsSession++;
			if (LootCommand.zombieBosses != -1) {
				LootCommand.zombieBosses++;
			}
			if (LootCommand.zombieBossesSession != 1) {
				LootCommand.zombieBossesSession++;
			}
			ConfigHandler.writeIntConfig("zombie", "revs", LootCommand.zombieRevs);
			ConfigHandler.writeIntConfig("zombie", "bossRNG", LootCommand.zombieBosses);
		} else if (message.contains("RARE DROP! (Foul Flesh)")) {
			LootCommand.zombieFoulFleshDrops++;
			LootCommand.zombieFoulFleshDropsSession++;
			ConfigHandler.writeIntConfig("zombie", "foulFleshDrops", LootCommand.zombieFoulFleshDrops);
		} else if (message.contains("VERY RARE DROP!  (Revenant Catalyst)")) {
			LootCommand.zombieRevCatas++;
			LootCommand.zombieRevCatasSession++;
			ConfigHandler.writeIntConfig("zombie", "revCatalyst", LootCommand.zombieRevCatas);
		} else if (message.contains("VERY RARE DROP!  (") && message.contains(" Pestilence Rune I)")) {
			LootCommand.zombiePestilences++;
			LootCommand.zombiePestilencesSession++;
			ConfigHandler.writeIntConfig("zombie", "pestilence", LootCommand.zombiePestilences);
		} else if (message.contains("VERY RARE DROP!  (Undead Catalyst)")) {
			LootCommand.zombieUndeadCatas++;
			LootCommand.zombieUndeadCatasSession++;
			ConfigHandler.writeIntConfig("zombie", "undeadCatalyst", LootCommand.zombieUndeadCatas);
		} else if (message.contains("CRAZY RARE DROP!  (Beheaded Horror)")) {
			zombieRNG = true;
			LootCommand.zombieBeheadeds++;
			LootCommand.zombieBeheadedsSession++;
			ConfigHandler.writeIntConfig("zombie", "beheaded", LootCommand.zombieBeheadeds);
			if (ToggleCommand.rngesusAlerts) Utils.createTitle(EnumChatFormatting.DARK_PURPLE + "BEHEADED HORROR!", 3);
		} else if (message.contains("CRAZY RARE DROP!  (") && message.contains(" Snake Rune I)")) {
			zombieRNG = true;
			LootCommand.zombieSnakes++;
			LootCommand.zombieSnakesSession++;
			ConfigHandler.writeIntConfig("zombie", "snake", LootCommand.zombieSnakes);
			if (ToggleCommand.rngesusAlerts) Utils.createTitle(EnumChatFormatting.DARK_GREEN + "SNAKE RUNE!", 3);
		} else if (message.contains("CRAZY RARE DROP!  (Scythe Blade)")) {
			zombieRNG = true;
			LootCommand.zombieScythes++;
			LootCommand.zombieScythesSession++;
			ConfigHandler.writeIntConfig("zombie", "scythe", LootCommand.zombieScythes);
			if (ToggleCommand.rngesusAlerts) Utils.createTitle(EnumChatFormatting.GOLD + "SCYTHE BLADE!", 5);
		} else if (message.contains("GOOD CATCH!")) { // Fishing
			LootCommand.goodCatches++;
			LootCommand.goodCatchesSession++;
			ConfigHandler.writeIntConfig("fishing", "goodCatch", LootCommand.goodCatches);
		} else if (message.contains("GREAT CATCH!")) {
			LootCommand.greatCatches++;
			LootCommand.greatCatchesSession++;
			ConfigHandler.writeIntConfig("fishing", "greatCatch", LootCommand.greatCatches);
		} else if (message.contains("A Squid appeared")) {
			LootCommand.squids++;
			LootCommand.squidsSession++;
			ConfigHandler.writeIntConfig("fishing", "squid", LootCommand.squids);
			increaseSeaCreatures();
		} else if (message.contains("You caught a Sea Walker")) {
			LootCommand.seaWalkers++;
			LootCommand.seaWalkersSession++;
			ConfigHandler.writeIntConfig("fishing", "seaWalker", LootCommand.seaWalkers);
			increaseSeaCreatures();
		} else if (message.contains("Pitch darkness reveals a Night Squid")) {
			LootCommand.nightSquids++;
			LootCommand.nightSquidsSession++;
			ConfigHandler.writeIntConfig("fishing", "nightSquid", LootCommand.nightSquids);
			increaseSeaCreatures();
		} else if (message.contains("You stumbled upon a Sea Guardian")) {
			LootCommand.seaGuardians++;
			LootCommand.seaGuardiansSession++;
			ConfigHandler.writeIntConfig("fishing", "seaGuardian", LootCommand.seaGuardians);
			increaseSeaCreatures();
		} else if (message.contains("It looks like you've disrupted the Sea Witch's brewing session. Watch out, she's furious")) {
			LootCommand.seaWitches++;
			LootCommand.seaWitchesSession++;
			ConfigHandler.writeIntConfig("fishing", "seaWitch", LootCommand.seaWitches);
			increaseSeaCreatures();
		} else if (message.contains("You reeled in a Sea Archer")) {
			LootCommand.seaArchers++;
			LootCommand.seaArchersSession++;
			ConfigHandler.writeIntConfig("fishing", "seaArcher", LootCommand.seaArchers);
			increaseSeaCreatures();
		} else if (message.contains("The Monster of the Deep has emerged")) {
			LootCommand.monsterOfTheDeeps++;
			LootCommand.monsterOfTheDeepsSession++;
			ConfigHandler.writeIntConfig("fishing", "monsterOfDeep", LootCommand.monsterOfTheDeeps);
			increaseSeaCreatures();
		} else if (message.contains("Huh? A Catfish")) {
			LootCommand.catfishes++;
			LootCommand.catfishesSession++;
			ConfigHandler.writeIntConfig("fishing", "catfish", LootCommand.catfishes);
			increaseSeaCreatures();
		} else if (message.contains("Is this even a fish? It's the Carrot King")) {
			LootCommand.carrotKings++;
			LootCommand.carrotKingsSession++;
			ConfigHandler.writeIntConfig("fishing", "carrotKing", LootCommand.carrotKings);
			increaseSeaCreatures();
		} else if (message.contains("Gross! A Sea Leech")) {
			LootCommand.seaLeeches++;
			LootCommand.seaLeechesSession++;
			ConfigHandler.writeIntConfig("fishing", "seaLeech", LootCommand.seaLeeches);
			increaseSeaCreatures();
		} else if (message.contains("You've discovered a Guardian Defender of the sea")) {
			LootCommand.guardianDefenders++;
			LootCommand.guardianDefendersSession++;
			ConfigHandler.writeIntConfig("fishing", "guardianDefender", LootCommand.guardianDefenders);
			increaseSeaCreatures();
		} else if (message.contains("You have awoken the Deep Sea Protector, prepare for a battle")) {
			LootCommand.deepSeaProtectors++;
			LootCommand.deepSeaProtectorsSession++;
			ConfigHandler.writeIntConfig("fishing", "deepSeaProtector", LootCommand.deepSeaProtectors);
			increaseSeaCreatures();
		} else if (message.contains("The Water Hydra has come to test your strength")) {
			LootCommand.hydras++;
			LootCommand.hydrasSession++;
			ConfigHandler.writeIntConfig("fishing", "hydra", LootCommand.hydras);
			increaseSeaCreatures();
		} else if (message.contains("The Sea Emperor arises from the depths")) {
			increaseSeaCreatures();

			LootCommand.seaEmperors++;
			LootCommand.empTime = System.currentTimeMillis() / 1000;
			LootCommand.empSCs = 0;
			LootCommand.seaEmperorsSession++;
			LootCommand.empTimeSession = System.currentTimeMillis() / 1000;
			LootCommand.empSCsSession = 0;
			ConfigHandler.writeIntConfig("fishing", "seaEmperor", LootCommand.seaEmperors);
			ConfigHandler.writeDoubleConfig("fishing", "empTime", LootCommand.empTime);
			ConfigHandler.writeIntConfig("fishing", "empSC", LootCommand.empSCs);
		} else if (message.contains("Frozen Steve fell into the pond long ago")) { // Fishing Winter
			LootCommand.frozenSteves++;
			LootCommand.frozenStevesSession++;
			ConfigHandler.writeIntConfig("fishing", "frozenSteve", LootCommand.frozenSteves);
			increaseSeaCreatures();
		} else if (message.contains("It's a snowman! He looks harmless")) {
			LootCommand.frostyTheSnowmans++;
			LootCommand.frostyTheSnowmansSession++;
			ConfigHandler.writeIntConfig("fishing", "snowman", LootCommand.frostyTheSnowmans);
			increaseSeaCreatures();
		} else if (message.contains("stole Jerry's Gifts...get them back")) {
			LootCommand.grinches++;
			LootCommand.grinchesSession++;
			ConfigHandler.writeIntConfig("fishing", "grinch", LootCommand.grinches);
			increaseSeaCreatures();
		} else if (message.contains("What is this creature")) {
			LootCommand.yetis++;
			LootCommand.yetiTime = System.currentTimeMillis() / 1000;
			LootCommand.yetiSCs = 0;
			LootCommand.yetisSession++;
			LootCommand.yetiTimeSession = System.currentTimeMillis() / 1000;
			LootCommand.yetiSCsSession = 0;
			ConfigHandler.writeIntConfig("fishing", "yeti", LootCommand.yetis);
			ConfigHandler.writeDoubleConfig("fishing", "yetiTime", LootCommand.yetiTime);
			ConfigHandler.writeIntConfig("fishing", "yetiSC", LootCommand.yetiSCs);
			increaseSeaCreatures();
		} else if (message.contains("A tiny fin emerges from the water, you've caught a Nurse Shark")) { // Fishing Festival
			LootCommand.nurseSharks++;
			LootCommand.nurseSharksSession++;
			ConfigHandler.writeIntConfig("fishing", "nurseShark", LootCommand.nurseSharks);
			increaseSeaCreatures();
		} else if (message.contains("You spot a fin as blue as the water it came from, it's a Blue Shark")) {
			LootCommand.blueSharks++;
			LootCommand.blueSharksSession++;
			ConfigHandler.writeIntConfig("fishing", "blueShark", LootCommand.blueSharks);
			increaseSeaCreatures();
		} else if (message.contains("A striped beast bounds from the depths, the wild Tiger Shark")) {
			LootCommand.tigerSharks++;
			LootCommand.tigerSharksSession++;
			ConfigHandler.writeIntConfig("fishing", "tigerShark", LootCommand.tigerSharks);
			increaseSeaCreatures();
		} else if (message.contains("Hide no longer, a Great White Shark has tracked your scent and thirsts for your blood")) {
			LootCommand.greatWhiteSharks++;
			LootCommand.greatWhiteSharksSession++;
			ConfigHandler.writeIntConfig("fishing", "greatWhiteShark", LootCommand.greatWhiteSharks);
			increaseSeaCreatures();
		} else if (message.contains("Phew! It's only a Scarecrow")) {
			LootCommand.scarecrows++;
			LootCommand.scarecrowsSession++;
			ConfigHandler.writeIntConfig("fishing", "scarecrow", LootCommand.scarecrows);
			increaseSeaCreatures();
		} else if (message.contains("You hear trotting from beneath the waves, you caught a Nightmare")) {
			LootCommand.nightmares++;
			LootCommand.nightmaresSession++;
			ConfigHandler.writeIntConfig("fishing", "nightmare", LootCommand.nightmares);
			increaseSeaCreatures();
		} else if (message.contains("It must be a full moon, a Werewolf appears")) {
			LootCommand.werewolfs++;
			LootCommand.werewolfsSession++;
			ConfigHandler.writeIntConfig("fishing", "werewolf", LootCommand.werewolfs);
			increaseSeaCreatures();
		} else if (message.contains("The spirit of a long lost Phantom Fisher has come to haunt you")) {
			LootCommand.phantomFishers++;
			LootCommand.phantomFishersSession++;
			ConfigHandler.writeIntConfig("fishing", "phantomFisher", LootCommand.phantomFishers);
			increaseSeaCreatures();
		} else if (message.contains("This can't be! The manifestation of death himself")) {
			LootCommand.grimReapers++;
			LootCommand.grimReapersSession++;
			ConfigHandler.writeIntConfig("fishing", "grimReaper", LootCommand.grimReapers);
			increaseSeaCreatures();
		} else if (message.contains("Dungeon starts in 1 second.")) { // Dungeons Stuff
		    dungeonStartTime = System.currentTimeMillis() / 1000 + 1;
		    bloodOpenTime = dungeonStartTime;
		    watcherClearTime = dungeonStartTime;
		    bossClearTime = dungeonStartTime;
		    witherDoors = 0;
		    dungeonDeaths = 0;
		    puzzleFails = 0;
		} else if (message.contains("The BLOOD DOOR has been opened!")) {
			bloodOpenTime = System.currentTimeMillis() / 1000;
		} else if (message.contains(" opened a WITHER door!")) {
			witherDoors++;
		} else if (message.contains(" and became a ghost.")) {
			dungeonDeaths++;
		} else if (message.contains(" Defeated ") && message.contains(" in ")) {
			bossClearTime = System.currentTimeMillis() / 1000;
		} else if (message.contains("EXTRA STATS ")) {
			List<String> scoreboard = ScoreboardHandler.getSidebarLines();
			int timeToAdd = 0;
			for (String s : scoreboard) {
				String sCleaned = ScoreboardHandler.cleanSB(s);
				if (sCleaned.contains("The Catacombs (")) {
					// Add time to floor
					if (sCleaned.contains("F1")) {
						LootCommand.f1TimeSpent = Math.floor(LootCommand.f1TimeSpent + timeToAdd);
						LootCommand.f1TimeSpentSession = Math.floor(LootCommand.f1TimeSpentSession + timeToAdd);
						ConfigHandler.writeDoubleConfig("catacombs", "floorOneTime", LootCommand.f1TimeSpent);
					} else if (sCleaned.contains("F2")) {
						LootCommand.f2TimeSpent = Math.floor(LootCommand.f2TimeSpent + timeToAdd);
						LootCommand.f2TimeSpentSession = Math.floor(LootCommand.f2TimeSpentSession + timeToAdd);
						ConfigHandler.writeDoubleConfig("catacombs", "floorTwoTime", LootCommand.f2TimeSpent);
					} else if (sCleaned.contains("F3")) {
						LootCommand.f3TimeSpent = Math.floor(LootCommand.f3TimeSpent + timeToAdd);
						LootCommand.f3TimeSpentSession = Math.floor(LootCommand.f3TimeSpentSession + timeToAdd);
						ConfigHandler.writeDoubleConfig("catacombs", "floorThreeTime", LootCommand.f3TimeSpent);
					} else if (sCleaned.contains("F4")) {
						LootCommand.f4TimeSpent = Math.floor(LootCommand.f4TimeSpent + timeToAdd);
						LootCommand.f4TimeSpentSession = Math.floor(LootCommand.f4TimeSpentSession + timeToAdd);
						ConfigHandler.writeDoubleConfig("catacombs", "floorFourTime", LootCommand.f4TimeSpent);
					} else if (sCleaned.contains("F5")) {
						LootCommand.f5TimeSpent = Math.floor(LootCommand.f5TimeSpent + timeToAdd);
						LootCommand.f5TimeSpentSession = Math.floor(LootCommand.f5TimeSpentSession + timeToAdd);
						ConfigHandler.writeDoubleConfig("catacombs", "floorFiveTime", LootCommand.f5TimeSpent);
					} else if (sCleaned.contains("F6")) {
						LootCommand.f6TimeSpent = Math.floor(LootCommand.f6TimeSpent + timeToAdd);
						LootCommand.f6TimeSpentSession = Math.floor(LootCommand.f6TimeSpentSession + timeToAdd);
						ConfigHandler.writeDoubleConfig("catacombs", "floorSixTime", LootCommand.f6TimeSpent);
					} else if (sCleaned.contains("F7")) {
						LootCommand.f7TimeSpent = Math.floor(LootCommand.f7TimeSpent + timeToAdd);
						LootCommand.f7TimeSpentSession = Math.floor(LootCommand.f7TimeSpentSession + timeToAdd);
						ConfigHandler.writeDoubleConfig("catacombs", "floorSevenTime", LootCommand.f7TimeSpent);
					}
				} else if (sCleaned.contains("Time Elapsed:")) {
					// Get floor time
					String time = sCleaned.substring(sCleaned.indexOf(":") + 2);
					time = time.replaceAll("\\s", "");
					int minutes = Integer.parseInt(time.substring(0, time.indexOf("m")));
					int seconds = Integer.parseInt(time.substring(time.indexOf("m") + 1, time.indexOf("s")));
					timeToAdd = (minutes * 60) + seconds;
				}
			}
		}

		if (wolfRNG) {
			LootCommand.wolfTime = System.currentTimeMillis() / 1000;
			LootCommand.wolfBosses = 0;
			LootCommand.wolfTimeSession = System.currentTimeMillis() / 1000;
			LootCommand.wolfBossesSession = 0;
			ConfigHandler.writeDoubleConfig("wolf", "timeRNG", LootCommand.wolfTime);
			ConfigHandler.writeIntConfig("wolf", "bossRNG", 0);
		}
		if (spiderRNG) {
			LootCommand.spiderTime = System.currentTimeMillis() / 1000;
			LootCommand.spiderBosses = 0;
			LootCommand.spiderTimeSession = System.currentTimeMillis() / 1000;
			LootCommand.spiderBossesSession = 0;
			ConfigHandler.writeDoubleConfig("spider", "timeRNG", LootCommand.spiderTime);
			ConfigHandler.writeIntConfig("spider", "bossRNG", 0);
		}
		if (zombieRNG) {
			LootCommand.zombieTime = System.currentTimeMillis() / 1000;
			LootCommand.zombieBosses = 0;
			LootCommand.zombieTimeSession = System.currentTimeMillis() / 1000;
			LootCommand.zombieBossesSession = 0;
			ConfigHandler.writeDoubleConfig("zombie", "timeRNG", LootCommand.zombieTime);
			ConfigHandler.writeIntConfig("zombie", "bossRNG", 0);
		}

		// Mythological Tracker
		if (message.contains("You dug out")) {
			if (message.contains(" coins!")) {
				double coinsEarned = Double.parseDouble(message.replaceAll("[^\\d]", ""));
				LootCommand.mythCoins += coinsEarned;
				LootCommand.mythCoinsSession += coinsEarned;
				ConfigHandler.writeDoubleConfig("mythological", "coins", LootCommand.mythCoins);
			} else if (message.contains("a Griffin Feather!")) {
				LootCommand.griffinFeathers++;
				LootCommand.griffinFeathersSession++;
				ConfigHandler.writeIntConfig("mythological", "griffinFeather", LootCommand.griffinFeathers);
			} else if (message.contains("a Crown of Greed!")) {
				LootCommand.crownOfGreeds++;
				LootCommand.crownOfGreedsSession++;
				ConfigHandler.writeIntConfig("mythological", "crownOfGreed", LootCommand.crownOfGreeds);
			} else if (message.contains("a Washed-up Souvenir!")) {
				LootCommand.washedUpSouvenirs++;
				LootCommand.washedUpSouvenirsSession++;
				ConfigHandler.writeIntConfig("mythological", "washedUpSouvenir", LootCommand.washedUpSouvenirs);
			} else if (message.contains("a Minos Hunter!")) {
				LootCommand.minosHunters++;
				LootCommand.minosHuntersSession++;
				ConfigHandler.writeIntConfig("mythological", "minosHunter", LootCommand.minosHunters);
			} else if (message.contains("Siamese Lynxes!")) {
				LootCommand.siameseLynxes++;
				LootCommand.siameseLynxesSession++;
				ConfigHandler.writeIntConfig("mythological", "siameseLynx", LootCommand.siameseLynxes);
			} else if (message.contains("a Minotaur!")) {
				LootCommand.minotaurs++;
				LootCommand.minotaursSession++;
				ConfigHandler.writeIntConfig("mythological", "minotaur", LootCommand.minotaurs);
			} else if (message.contains("a Gaia Construct!")) {
				LootCommand.gaiaConstructs++;
				LootCommand.gaiaConstructsSession++;
				ConfigHandler.writeIntConfig("mythological", "gaiaConstruct", LootCommand.gaiaConstructs);
			} else if (message.contains("a Minos Champion!")) {
				LootCommand.minosChampions++;
				LootCommand.minosChampionsSession++;
				ConfigHandler.writeIntConfig("mythological", "minosChampion", LootCommand.minosChampions);
			} else if (message.contains("a Minos Inquisitor!")) {
				LootCommand.minosInquisitors++;
				LootCommand.minosInquisitorsSession++;
				ConfigHandler.writeIntConfig("mythological", "minosInquisitor", LootCommand.minosInquisitors);
			}
		}

		// Dungeons Trackers
		if (message.contains("    ")) {
			if (message.contains("Recombobulator 3000")) {
				LootCommand.recombobulators++;
				LootCommand.recombobulatorsSession++;
				ConfigHandler.writeIntConfig("catacombs", "recombobulator", LootCommand.recombobulators);
			} else if (message.contains("Fuming Potato Book")) {
				LootCommand.fumingPotatoBooks++;
				LootCommand.fumingPotatoBooksSession++;
				ConfigHandler.writeIntConfig("catacombs", "fumingBooks", LootCommand.fumingPotatoBooks);
			} else if (message.contains("Bonzo's Staff")) { // F1
				LootCommand.bonzoStaffs++;
				LootCommand.bonzoStaffsSession++;
				ConfigHandler.writeIntConfig("catacombs", "bonzoStaff", LootCommand.bonzoStaffs);
			} else if (message.contains("Scarf's Studies")) { // F2
				LootCommand.scarfStudies++;
				LootCommand.scarfStudiesSession++;
				ConfigHandler.writeIntConfig("catacombs", "scarfStudies", LootCommand.scarfStudies);
			} else if (message.contains("Adaptive Helmet")) { // F3
				LootCommand.adaptiveHelms++;
				LootCommand.adaptiveHelmsSession++;
				ConfigHandler.writeIntConfig("catacombs", "adaptiveHelm", LootCommand.adaptiveHelms);
			} else if (message.contains("Adaptive Chestplate")) {
				LootCommand.adaptiveChests++;
				LootCommand.adaptiveChestsSession++;
				ConfigHandler.writeIntConfig("catacombs", "adaptiveChest", LootCommand.adaptiveChests);
			} else if (message.contains("Adaptive Leggings")) {
				LootCommand.adaptiveLegs++;
				LootCommand.adaptiveLegsSession++;
				ConfigHandler.writeIntConfig("catacombs", "adaptiveLegging", LootCommand.adaptiveLegs);
			} else if (message.contains("Adaptive Boots")) {
				LootCommand.adaptiveBoots++;
				LootCommand.adaptiveBootsSession++;
				ConfigHandler.writeIntConfig("catacombs", "adaptiveBoot", LootCommand.adaptiveBoots);
			} else if (message.contains("Adaptive Blade")) {
				LootCommand.adaptiveSwords++;
				LootCommand.adaptiveSwordsSession++;
				ConfigHandler.writeIntConfig("catacombs", "adaptiveSword", LootCommand.adaptiveSwords);
			} else if (message.contains("Spirit Wing")) { // F4
				LootCommand.spiritWings++;
				LootCommand.spiritWingsSession++;
				ConfigHandler.writeIntConfig("catacombs", "spiritWing", LootCommand.spiritWings);
			} else if (message.contains("Spirit Bone")) {
				LootCommand.spiritBones++;
				LootCommand.spiritBonesSession++;
				ConfigHandler.writeIntConfig("catacombs", "spiritBone", LootCommand.spiritBones);
			} else if (message.contains("Spirit Boots")) {
				LootCommand.spiritBoots++;
				LootCommand.spiritBootsSession++;
				ConfigHandler.writeIntConfig("catacombs", "spiritBoot", LootCommand.spiritBoots);
			} else if (message.contains("[Lvl 1] Spirit")) {
				String formattedMessage = event.message.getFormattedText();
				// Unicode colour code messes up here, just gonna remove the symbols
				if (formattedMessage.contains("5Spirit")) {
					LootCommand.epicSpiritPets++;
					LootCommand.epicSpiritPetsSession++;
					ConfigHandler.writeIntConfig("catacombs", "spiritPetEpic", LootCommand.epicSpiritPets);
				} else if (formattedMessage.contains("6Spirit")) {
					LootCommand.legSpiritPets++;
					LootCommand.legSpiritPetsSession++;
					ConfigHandler.writeIntConfig("catacombs", "spiritPetLeg", LootCommand.legSpiritPets);
				}
			} else if (message.contains("Spirit Sword")) {
				LootCommand.spiritSwords++;
				LootCommand.spiritSwordsSession++;
				ConfigHandler.writeIntConfig("catacombs", "spiritSword", LootCommand.spiritSwords);
			} else if (message.contains("Spirit Bow")) {
				LootCommand.spiritBows++;
				LootCommand.spiritBowsSession++;
				ConfigHandler.writeIntConfig("catacombs", "spiritBow", LootCommand.spiritBows);
			} else if (message.contains("Warped Stone")) { // F5
				LootCommand.warpedStones++;
				LootCommand.warpedStonesSession++;
				ConfigHandler.writeIntConfig("catacombs", "warpedStone", LootCommand.warpedStones);
			} else if (message.contains("Shadow Assassin Helmet")) {
				LootCommand.shadowAssHelms++;
				LootCommand.shadowAssHelmsSession++;
				ConfigHandler.writeIntConfig("catacombs", "shadowAssassinHelm", LootCommand.shadowAssHelms);
			} else if (message.contains("Shadow Assassin Chestplate")) {
				LootCommand.shadowAssChests++;
				LootCommand.shadowAssChestsSession++;
				ConfigHandler.writeIntConfig("catacombs", "shadowAssassinChest", LootCommand.shadowAssChests);
			} else if (message.contains("Shadow Assassin Leggings")) {
				LootCommand.shadowAssLegs++;
				LootCommand.shadowAssLegsSession++;
				ConfigHandler.writeIntConfig("catacombs", "shadowAssassinLegging", LootCommand.shadowAssLegs);
			} else if (message.contains("Shadow Assassin Boots")) {
				LootCommand.shadowAssBoots++;
				LootCommand.shadowAssBootsSession++;
				ConfigHandler.writeIntConfig("catacombs", "shadowAssassinBoot", LootCommand.shadowAssBoots);
			} else if (message.contains("Livid Dagger")) {
				LootCommand.lividDaggers++;
				LootCommand.lividDaggersSession++;
				ConfigHandler.writeIntConfig("catacombs", "lividDagger", LootCommand.lividDaggers);
			} else if (message.contains("Shadow Fury")) {
				LootCommand.shadowFurys++;
				LootCommand.shadowFurysSession++;
				ConfigHandler.writeIntConfig("catacombs", "shadowFury", LootCommand.shadowFurys);
			} else if (message.contains("Ancient Rose")) { // F6
				LootCommand.ancientRoses++;
				LootCommand.ancientRosesSession++;
				ConfigHandler.writeIntConfig("catacombs", "ancientRose", LootCommand.ancientRoses);
			} else if (message.contains("Precursor Eye")) {
				LootCommand.precursorEyes++;
				LootCommand.precursorEyesSession++;
				ConfigHandler.writeIntConfig("catacombs", "precursorEye", LootCommand.precursorEyes);
			} else if (message.contains("Giant's Sword")) {
				LootCommand.giantsSwords++;
				LootCommand.giantsSwordsSession++;
				ConfigHandler.writeIntConfig("catacombs", "giantsSword", LootCommand.giantsSwords);
			} else if (message.contains("Necromancer Lord Helmet")) {
				LootCommand.necroLordHelms++;
				LootCommand.necroLordHelmsSession++;
				ConfigHandler.writeIntConfig("catacombs", "necroLordHelm", LootCommand.necroLordHelms);
			} else if (message.contains("Necromancer Lord Chestplate")) {
				LootCommand.necroLordChests++;
				LootCommand.necroLordChestsSession++;
				ConfigHandler.writeIntConfig("catacombs", "necroLordChest", LootCommand.necroLordChests);
			} else if (message.contains("Necromancer Lord Leggings")) {
				LootCommand.necroLordLegs++;
				LootCommand.necroLordLegsSession++;
				ConfigHandler.writeIntConfig("catacombs", "necroLordLegging", LootCommand.necroLordLegs);
			} else if (message.contains("Necromancer Lord Boots")) {
				LootCommand.necroLordBoots++;
				LootCommand.necroLordBootsSession++;
				ConfigHandler.writeIntConfig("catacombs", "necroLordBoot", LootCommand.necroLordBoots);
			} else if (message.contains("Necromancer Sword")) {
				LootCommand.necroSwords++;
				LootCommand.necroSwordsSession++;
				ConfigHandler.writeIntConfig("catacombs", "necroSword", LootCommand.necroSwords);
			} else if (message.contains("Wither Blood")) { // F7
				LootCommand.witherBloods++;
				LootCommand.witherBloodsSession++;
				ConfigHandler.writeIntConfig("catacombs", "witherBlood", LootCommand.witherBloods);
			} else if (message.contains("Wither Cloak")) {
				LootCommand.witherCloaks++;
				LootCommand.witherCloaksSession++;
				ConfigHandler.writeIntConfig("catacombs", "witherCloak", LootCommand.witherCloaks);
			} else if (message.contains("Implosion")) {
				LootCommand.implosions++;
				LootCommand.implosionsSession++;
				ConfigHandler.writeIntConfig("catacombs", "implosion", LootCommand.implosions);
			} else if (message.contains("Wither Shield")) {
				LootCommand.witherShields++;
				LootCommand.witherShieldsSession++;
				ConfigHandler.writeIntConfig("catacombs", "witherShield", LootCommand.witherShields);
			} else if (message.contains("Shadow Warp")) {
				LootCommand.shadowWarps++;
				LootCommand.shadowWarpsSession++;
				ConfigHandler.writeIntConfig("catacombs", "shadowWarp", LootCommand.shadowWarps);
			} else if (message.contains("Necron's Handle")) {
				LootCommand.necronsHandles++;
				LootCommand.necronsHandlesSession++;
				ConfigHandler.writeIntConfig("catacombs", "necronsHandle", LootCommand.necronsHandles);
			} else if (message.contains("Auto Recombobulator")) {
				LootCommand.autoRecombs++;
				LootCommand.autoRecombsSession++;
				ConfigHandler.writeIntConfig("catacombs", "autoRecomb", LootCommand.autoRecombs);
			} else if (message.contains("Wither Helmet")) {
				LootCommand.witherHelms++;
				LootCommand.witherHelmsSession++;
				ConfigHandler.writeIntConfig("catacombs", "witherHelm", LootCommand.witherHelms);
			} else if (message.contains("Wither Chestplate")) {
				LootCommand.witherChests++;
				LootCommand.witherChestsSession++;
				ConfigHandler.writeIntConfig("catacombs", "witherChest", LootCommand.witherChests);
			} else if (message.contains("Wither Leggings")) {
				LootCommand.witherLegs++;
				LootCommand.witherLegsSession++;
				ConfigHandler.writeIntConfig("catacombs", "witherLegging", LootCommand.witherLegs);
			} else if (message.contains("Wither Boots")) {
				LootCommand.witherBoots++;
				LootCommand.witherBootsSession++;
				ConfigHandler.writeIntConfig("catacombs", "witherBoot", LootCommand.witherBoots);
			}
		}

		// Chat Maddox
		if (message.contains("[OPEN MENU]")) {
			List<IChatComponent> listOfSiblings = event.message.getSiblings();
			for (IChatComponent sibling : listOfSiblings) {
				if (sibling.getUnformattedText().contains("[OPEN MENU]")) {
					lastMaddoxCommand = sibling.getChatStyle().getChatClickEvent().getValue();
					lastMaddoxTime = System.currentTimeMillis() / 1000;
				}
			}
			if (ToggleCommand.chatMaddoxToggled) Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(MAIN_COLOUR + "Open chat then click anywhere on-screen to open Maddox"));
		}

		// Spirit Bear alerts
		if (ToggleCommand.spiritBearAlerts && message.contains("The Spirit Bear has appeared!")) {
			Utils.createTitle(EnumChatFormatting.DARK_PURPLE + "SPIRIT BEAR", 2);
		}
    }

    @SubscribeEvent
    public void renderPlayerInfo(final RenderGameOverlayEvent.Post event) {
        if (usingLabymod && !(Minecraft.getMinecraft().ingameGUI instanceof GuiIngameForge)) return;
        if (event.type != RenderGameOverlayEvent.ElementType.EXPERIENCE && event.type != RenderGameOverlayEvent.ElementType.JUMPBAR)
            return;
        if (Minecraft.getMinecraft().currentScreen instanceof EditLocationsGui) return;
        MinecraftForge.EVENT_BUS.post(new RenderOverlay());
    }

    // LabyMod Support
    @SubscribeEvent
    public void renderPlayerInfoLabyMod(final RenderGameOverlayEvent event) {
        if (!usingLabymod) return;
        if (event.type != null) return;
        renderEverything();
    }

    public void renderEverything() {
        if (Minecraft.getMinecraft().currentScreen instanceof EditLocationsGui) return;
        MinecraftForge.EVENT_BUS.post(new RenderOverlay());
    }

    @SubscribeEvent
    public void renderPlayerInfo(RenderOverlay event) {
        if (showTitle) {
            Utils.drawTitle(titleText);
        }
        if (showSkill) {
            new TextRenderer(mc, skillText, MoveCommand.skill50XY[0], MoveCommand.skill50XY[1], ScaleCommand.skill50Scale);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void onTooltipLow(ItemTooltipEvent event) {
        if (event.toolTip == null) return;

        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP player = mc.thePlayer;

        if (mc.currentScreen instanceof GuiChest) {
            ContainerChest chest = (ContainerChest) player.openContainer;
            IInventory inv = chest.getLowerChestInventory();
            String chestName = inv.getDisplayName().getUnformattedText();

            if (ToggleCommand.hideTooltipsInExperimentAddonsToggled && (chestName.startsWith("Ultrasequencer (") || chestName.startsWith("Chronomatron ("))) {
                event.toolTip.clear();
            }

            if (ToggleCommand.clickInOrderToggled && chestName.equals("Click in order!")) {
                event.toolTip.clear();
            }

        }
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (event.phase != Phase.START) return;

        EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;

        if (mc.currentScreen == null && System.currentTimeMillis() - lastInteractTime >= 250L) {
            slotIn = -1;
            lastSlot = -1;
            mazeId = 0;
        }
        if (keyBindings[6].isKeyDown())
            for (int i = 0; i <= 8; i++) {
                ItemStack item = player.inventory.getStackInSlot(i);
                if ((item != null && item.getDisplayName().contains("Hyperion")) || (item != null && item.getDisplayName().contains("Aspect of the End"))) {
                    player.inventory.currentItem = i;
                    mc.playerController.sendUseItem(mc.thePlayer, world, player.inventory.getStackInSlot(i));
                    break;
                }
            }
        if (keyBindings[7].isKeyDown()) {
            if (mc.objectMouseOver.getBlockPos() == null) return;
            Block block = (Minecraft.getMinecraft()).theWorld.getBlockState(mc.objectMouseOver.getBlockPos()).getBlock();
            ArrayList<Block> interactables = new ArrayList<>(Arrays.asList(new Block[] {
                    Blocks.acacia_door, Blocks.anvil, Blocks.beacon, Blocks.bed, Blocks.birch_door, Blocks.brewing_stand, Blocks.command_block, Blocks.crafting_table, Blocks.chest, Blocks.dark_oak_door,
                    Blocks.daylight_detector, Blocks.daylight_detector_inverted, Blocks.dispenser, Blocks.dropper, Blocks.enchanting_table, Blocks.ender_chest, Blocks.furnace, Blocks.hopper, Blocks.jungle_door, Blocks.lever,
                    Blocks.noteblock, Blocks.powered_comparator, Blocks.unpowered_comparator, Blocks.powered_repeater, Blocks.unpowered_repeater, Blocks.standing_sign, Blocks.wall_sign, Blocks.trapdoor, Blocks.trapped_chest, Blocks.wooden_button,
                    Blocks.stone_button, Blocks.oak_door, Blocks.skull }));
            if (!interactables.contains(block)) {
                world.setBlockToAir(mc.objectMouseOver.getBlockPos());
            }
        }

        // Checks every second
        tickAmount++;
        if (tickAmount % 20 == 0) {
            if (player != null) {
                Utils.checkForSkyblock();
                Utils.checkForDungeons();
            }

            if (DisplayCommand.auto && world != null && player != null) {
                List<String> scoreboard = ScoreboardHandler.getSidebarLines();
                boolean found = false;
                for (String s : scoreboard) {
                    String sCleaned = ScoreboardHandler.cleanSB(s);
                    if (sCleaned.contains("Sven Packmaster")) {
                        DisplayCommand.display = "wolf";
                        found = true;
                    } else if (sCleaned.contains("Tarantula Broodfather")) {
                        DisplayCommand.display = "spider";
                        found = true;
                    } else if (sCleaned.contains("Revenant Horror")) {
                        DisplayCommand.display = "zombie";
                        found = true;
                    } else if (sCleaned.contains("The Catacombs (")) {
                        if (sCleaned.contains("F1")) {
                            DisplayCommand.display = "catacombs_floor_one";
                        } else if (sCleaned.contains("F2")) {
                            DisplayCommand.display = "catacombs_floor_two";
                        } else if (sCleaned.contains("F3")) {
                            DisplayCommand.display = "catacombs_floor_three";
                        } else if (sCleaned.contains("F4")) {
                            DisplayCommand.display = "catacombs_floor_four";
                        } else if (sCleaned.contains("F5")) {
                            DisplayCommand.display = "catacombs_floor_five";
                        } else if (sCleaned.contains("F6")) {
                            DisplayCommand.display = "catacombs_floor_six";
                        } else if (sCleaned.contains("F7")) {
                            DisplayCommand.display = "catacombs_floor_seven";
                        }
                        found = true;
                    }
                }
                for (int i = 0; i < 8; i++) {
                    ItemStack hotbarItem = player.inventory.getStackInSlot(i);
                    if (hotbarItem == null) continue;
                    if (hotbarItem.getDisplayName().contains("Ancestral Spade")) {
                        DisplayCommand.display = "mythological";
                        found = true;
                    }
                }
                if (!found) DisplayCommand.display = "off";
                ConfigHandler.writeStringConfig("misc", "display", DisplayCommand.display);
            }

            if (ToggleCommand.creeperToggled && Utils.inDungeons && world != null && player != null) {
                double x = player.posX;
                double y = player.posY;
                double z = player.posZ;
                // Find creepers nearby
                AxisAlignedBB creeperScan = new AxisAlignedBB(x - 14, y - 8, z - 13, x + 14, y + 8, z + 13); // 28x16x26 cube
                List<EntityCreeper> creepers = world.getEntitiesWithinAABB(EntityCreeper.class, creeperScan);
                // Check if creeper is nearby
                if (creepers.size() > 0 && !creepers.get(0).isInvisible()) { // Don't show Wither Cloak creepers
                    EntityCreeper creeper = creepers.get(0);
                    // Start creeper line drawings
                    creeperLines.clear();
                    if (!drawCreeperLines) creeperLocation = new Vec3(creeper.posX, creeper.posY + 1, creeper.posZ);
                    drawCreeperLines = true;
                    // Search for nearby sea lanterns and prismarine blocks
                    BlockPos point1 = new BlockPos(creeper.posX - 14, creeper.posY - 7, creeper.posZ - 13);
                    BlockPos point2 = new BlockPos(creeper.posX + 14, creeper.posY + 10, creeper.posZ + 13);
                    Iterable<BlockPos> blocks = BlockPos.getAllInBox(point1, point2);
                    for (BlockPos blockPos : blocks) {
                        Block block = world.getBlockState(blockPos).getBlock();
                        if (block == Blocks.sea_lantern || block == Blocks.prismarine) {
                            // Connect block to nearest block on opposite side
                            Vec3 startBlock = new Vec3(blockPos.getX() + 0.5, blockPos.getY() + 0.5, blockPos.getZ() + 0.5);
                            BlockPos oppositeBlock = Utils.getFirstBlockPosAfterVectors(mc, startBlock, creeperLocation, 10, 20);
                            BlockPos endBlock = Utils.getNearbyBlock(mc, oppositeBlock, Blocks.sea_lantern, Blocks.prismarine);
                            if (endBlock != null && startBlock.yCoord > 68 && endBlock.getY() > 68) { // Don't create line underground
                                // Add to list for drawing
                                Vec3[] insertArray = {startBlock, new Vec3(endBlock.getX() + 0.5, endBlock.getY() + 0.5, endBlock.getZ() + 0.5)};
                                creeperLines.add(insertArray);
                            }
                        }
                    }
                } else {
                    drawCreeperLines = false;
                }
            }

            if (ToggleCommand.waterToggled && Utils.inDungeons && world != null && player != null) {
                // multi thread block checking
                new Thread(() -> {
                    prevInWaterRoom = inWaterRoom;
                    inWaterRoom = false;
                    boolean foundPiston = false;
                    boolean done = false;
                    for (int x = (int) (player.posX - 13); x <= player.posX + 13; x++) {
                        for (int z = (int) (player.posZ - 13); z <= player.posZ + 13; z++) {
                            BlockPos blockPos = new BlockPos(x, 54, z);
                            if (world.getBlockState(blockPos).getBlock() == Blocks.sticky_piston) {
                                foundPiston = true;
                                break;
                            }
                        }
                        if (foundPiston) break;
                    }

                    if (foundPiston) {
                        for (int x = (int) (player.posX - 25); x <= player.posX + 25; x++) {
                            for (int z = (int) (player.posZ - 25); z <= player.posZ + 25; z++) {
                                BlockPos blockPos = new BlockPos(x, 82, z);
                                if (world.getBlockState(blockPos).getBlock() == Blocks.piston_head) {
                                    inWaterRoom = true;
                                    if (!prevInWaterRoom) {
                                        boolean foundGold = false;
                                        boolean foundClay = false;
                                        boolean foundEmerald = false;
                                        boolean foundQuartz = false;
                                        boolean foundDiamond = false;

                                        // Detect first blocks near water stream
                                        BlockPos scan1 = new BlockPos(x + 1, 78, z + 1);
                                        BlockPos scan2 = new BlockPos(x - 1, 77, z - 1);
                                        Iterable<BlockPos> blocks = BlockPos.getAllInBox(scan1, scan2);
                                        for (BlockPos puzzleBlockPos : blocks) {
                                            Block block = world.getBlockState(puzzleBlockPos).getBlock();
                                            if (block == Blocks.gold_block) {
                                                foundGold = true;
                                            } else if (block == Blocks.hardened_clay) {
                                                foundClay = true;
                                            } else if (block == Blocks.emerald_block) {
                                                foundEmerald = true;
                                            } else if (block == Blocks.quartz_block) {
                                                foundQuartz = true;
                                            } else if (block == Blocks.diamond_block) {
                                                foundDiamond = true;
                                            }
                                        }

                                        int variant = 0;
                                        if (foundGold && foundClay) {
                                            variant = 1;
                                        } else if (foundEmerald && foundQuartz) {
                                            variant = 2;
                                        } else if (foundQuartz && foundDiamond) {
                                            variant = 3;
                                        } else if (foundGold && foundQuartz) {
                                            variant = 4;
                                        }

                                        // Return solution
                                        String purple;
                                        String orange;
                                        String blue;
                                        String green;
                                        String red;
                                        switch (variant) {
                                            case 1:
                                                purple = EnumChatFormatting.WHITE + "Quartz, " + EnumChatFormatting.YELLOW + "Gold, " + EnumChatFormatting.AQUA + "Diamond, " + EnumChatFormatting.RED + "Clay";
                                                orange = EnumChatFormatting.YELLOW + "Gold, " + EnumChatFormatting.DARK_GRAY + "Coal, " + EnumChatFormatting.GREEN + "Emerald";
                                                blue = EnumChatFormatting.WHITE + "Quartz, " + EnumChatFormatting.YELLOW + "Gold, " + EnumChatFormatting.GREEN + "Emerald, " + EnumChatFormatting.RED + "Clay";
                                                green = EnumChatFormatting.GREEN + "Emerald";
                                                red = EnumChatFormatting.GRAY + "None";
                                                break;
                                            case 2:
                                                purple = EnumChatFormatting.DARK_GRAY + "Coal";
                                                orange = EnumChatFormatting.WHITE + "Quartz, " + EnumChatFormatting.YELLOW + "Gold, " + EnumChatFormatting.GREEN + "Emerald, " + EnumChatFormatting.RED + "Clay";
                                                blue = EnumChatFormatting.WHITE + "Quartz, " + EnumChatFormatting.AQUA + "Diamond, " + EnumChatFormatting.GREEN + "Emerald";
                                                green = EnumChatFormatting.WHITE + "Quartz, " + EnumChatFormatting.GREEN + "Emerald";
                                                red = EnumChatFormatting.WHITE + "Quartz, " + EnumChatFormatting.DARK_GRAY + "Coal, " + EnumChatFormatting.GREEN + "Emerald";
                                                break;
                                            case 3:
                                                purple = EnumChatFormatting.WHITE + "Quartz, " + EnumChatFormatting.YELLOW + "Gold, " + EnumChatFormatting.AQUA + "Diamond";
                                                orange = EnumChatFormatting.GREEN + "Emerald";
                                                blue = EnumChatFormatting.WHITE + "Quartz, " + EnumChatFormatting.AQUA + "Diamond";
                                                green = EnumChatFormatting.GRAY + "None";
                                                red = EnumChatFormatting.YELLOW + "Gold, " + EnumChatFormatting.GREEN + "Emerald";
                                                break;
                                            case 4:
                                                purple = EnumChatFormatting.WHITE + "Quartz, " + EnumChatFormatting.YELLOW + "Gold, " + EnumChatFormatting.GREEN + "Emerald, " + EnumChatFormatting.RED + "Clay";
                                                orange = EnumChatFormatting.YELLOW + "Gold, " + EnumChatFormatting.DARK_GRAY + "Coal";
                                                blue = EnumChatFormatting.WHITE + "Quartz, " + EnumChatFormatting.YELLOW + "Gold, " + EnumChatFormatting.DARK_GRAY + "Coal, " + EnumChatFormatting.GREEN + "Emerald, " + EnumChatFormatting.RED + "Clay";
                                                green = EnumChatFormatting.YELLOW + "Gold, " + EnumChatFormatting.GREEN + "Emerald";
                                                red = EnumChatFormatting.YELLOW + "Gold, " + EnumChatFormatting.AQUA + "Diamond, " + EnumChatFormatting.GREEN + "Emerald, " + EnumChatFormatting.RED + "Clay";
                                                break;
                                            default:
                                                purple = orange = blue = green = red = ERROR_COLOUR + "Error detecting water puzzle variant.";
                                                break;
                                        }
                                        waterAnswers = MAIN_COLOUR + "The following levers must be down:\n" +
                                                       EnumChatFormatting.DARK_PURPLE + "Purple: " + purple + "\n" +
                                                       EnumChatFormatting.GOLD + "Orange: " + orange + "\n" +
                                                       EnumChatFormatting.BLUE + "Blue: " + blue + "\n" +
                                                       EnumChatFormatting.GREEN + "Green: " + green + "\n" +
                                                       EnumChatFormatting.RED + "Red: " + red;
                                        done = true;
                                        break;
                                    }
                                }
                            }
                            if (done) break;
                        }
                    } else {
                        waterAnswers = null;
                    }
                }).start();
            }

            if (ToggleCommand.lividSolverToggled && Utils.inDungeons && !foundLivid && world != null) {
                boolean inF5 = false;

                List<String> scoreboard = ScoreboardHandler.getSidebarLines();
                for (String s : scoreboard) {
                    String sCleaned = ScoreboardHandler.cleanSB(s);
                    if (sCleaned.contains("The Catacombs (F5)")) {
                        inF5 = true;
                        break;
                    }
                }

                if (inF5) {
                    List<Entity> loadedLivids = new ArrayList<>();
                    List<Entity> entities = world.getLoadedEntityList();
                    for (Entity entity : entities) {
                        String name = entity.getName();
                        if (name.contains("Livid") && name.length() > 5 && name.charAt(1) == name.charAt(5) && !loadedLivids.contains(entity)) {
                            loadedLivids.add(entity);
                        }
                    }
                    if (loadedLivids.size() > 8) {
                        livid = loadedLivids.get(0);
                        foundLivid = true;
                    }
                }
            }

            if (ToggleCommand.ticTacToeToggled && Utils.inDungeons && world != null && player != null) {
                correctTicTacToeButton = null;
                AxisAlignedBB aabb = new AxisAlignedBB(player.posX - 6, player.posY - 6, player.posZ - 6, player.posX + 6, player.posY + 6, player.posZ + 6);
                List<EntityItemFrame> itemFrames = world.getEntitiesWithinAABB(EntityItemFrame.class, aabb);
                List<EntityItemFrame> itemFramesWithMaps = new ArrayList<>();
                // Find how many item frames have maps already placed
                for (EntityItemFrame itemFrame : itemFrames) {
                    ItemStack item = itemFrame.getDisplayedItem();
                    if (item == null || !(item.getItem() instanceof ItemMap)) continue;
                    MapData mapData = ((ItemMap) item.getItem()).getMapData(item, world);
                    if (mapData == null) continue;

                    itemFramesWithMaps.add(itemFrame);
                }

                // Only run when it's your turn
                if (itemFramesWithMaps.size() != 9 && itemFramesWithMaps.size() % 2 == 1) {
                    char[][] board = new char[3][3];
                    BlockPos leftmostRow = null;
                    int sign = 1;
                    char facing = 'X';
                    for (EntityItemFrame itemFrame : itemFramesWithMaps) {
                        ItemStack map = itemFrame.getDisplayedItem();
                        MapData mapData = ((ItemMap) map.getItem()).getMapData(map, world);

                        // Find position on board
                        int row = 0;
                        int column;
                        sign = 1;

                        if (itemFrame.facingDirection == EnumFacing.SOUTH || itemFrame.facingDirection == EnumFacing.WEST) {
                            sign = -1;
                        }

                        BlockPos itemFramePos = new BlockPos(itemFrame.posX, Math.floor(itemFrame.posY), itemFrame.posZ);
                        for (int i = 2; i >= 0; i--) {
                            int realI = i * sign;
                            BlockPos blockPos = itemFramePos;
                            if (itemFrame.posX % 0.5 == 0) {
                                blockPos = itemFramePos.add(realI, 0, 0);
                            } else if (itemFrame.posZ % 0.5 == 0) {
                                blockPos = itemFramePos.add(0, 0, realI);
                                facing = 'Z';
                            }
                            Block block = world.getBlockState(blockPos).getBlock();
                            if (block == Blocks.air || block == Blocks.stone_button) {
                                leftmostRow = blockPos;
                                row = i;
                                break;
                            }
                        }

                        if (itemFrame.posY == 72.5) {
                            column = 0;
                        } else if (itemFrame.posY == 71.5) {
                            column = 1;
                        } else if (itemFrame.posY == 70.5) {
                            column = 2;
                        } else {
                            continue;
                        }

                        // Get colour
                        // Middle pixel = 64*128 + 64 = 8256
                        int colourInt = mapData.colors[8256] & 255;
                        if (colourInt == 114) {
                            board[column][row] = 'X';
                        } else if (colourInt == 33) {
                            board[column][row] = 'O';
                        }
                    }
                    System.out.println("Board: " + Arrays.deepToString(board));

                    // Draw best move
                    int bestMove = TicTacToeUtils.getBestMove(board) - 1;
                    System.out.println("Best move slot: " + bestMove);
                    if (leftmostRow != null) {
                        double drawX = facing == 'X' ? leftmostRow.getX() - sign * (bestMove % 3) : leftmostRow.getX();
                        double drawY = 72 - Math.floor(bestMove / 3);
                        double drawZ = facing == 'Z' ? leftmostRow.getZ() - sign * (bestMove % 3) : leftmostRow.getZ();

                        correctTicTacToeButton = new AxisAlignedBB(drawX, drawY, drawZ, drawX + 1, drawY + 1, drawZ + 1);
                    }
                }
            }

            tickAmount = 0;
        }

        // Checks 5 times per second
        if (tickAmount % 4 == 0) {
            if (ToggleCommand.blazeToggled && Utils.inDungeons && world != null) {
                List<Entity> entities = world.getLoadedEntityList();
                int highestHealth = 0;
                highestBlaze = null;
                int lowestHealth = 99999999;
                lowestBlaze = null;

                for (Entity entity : entities) {
                    if (entity.getName().contains("Blaze") && entity.getName().contains("/")) {
                        String blazeName = StringUtils.stripControlCodes(entity.getName());
                        try {
                            int health = Integer.parseInt(blazeName.substring(blazeName.indexOf("/") + 1, blazeName.length() - 1));
                            if (health > highestHealth) {
                                highestHealth = health;
                                highestBlaze = entity;
                            }
                            if (health < lowestHealth) {
                                lowestHealth = health;
                                lowestBlaze = entity;
                            }
                        } catch (NumberFormatException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }

        // Checks 10 times per second
        if (tickAmount % 2 == 0) {
            if (ToggleCommand.lowHealthNotifyToggled && Utils.inDungeons && world != null) {
                List<String> scoreboard = ScoreboardHandler.getSidebarLines();
                for (String score : scoreboard) {
                    if (score.endsWith("❤") && score.matches(".*§c\\d.*")) {
                        String name = score.substring(score.indexOf(" ") + 1);
                        Utils.createTitle(EnumChatFormatting.RED + "LOW HEALTH!\n" + name, 1);
                        break;
                    }
                }
            }
        }

        // Runs 20 times per second
        if (mc.currentScreen instanceof GuiChest) {
            if (player == null) return;
            ContainerChest chest = (ContainerChest) player.openContainer;
            List<Slot> invSlots = ((GuiChest) mc.currentScreen).inventorySlots.inventorySlots;
            String chestName = chest.getLowerChestInventory().getDisplayName().getUnformattedText().trim();

            if (ToggleCommand.ultrasequencerToggled && chestName.startsWith("Ultrasequencer (")) {
                if (invSlots.get(49).getStack() != null && invSlots.get(49).getStack().getDisplayName().equals("§aRemember the pattern!")) {
                    for (int i = 9; i <= 44; i++) {
                        if (invSlots.get(i) == null || invSlots.get(i).getStack() == null) continue;
                        String itemName = StringUtils.stripControlCodes(invSlots.get(i).getStack().getDisplayName());
                        if (itemName.matches("\\d+")) {
                            int number = Integer.parseInt(itemName);
                            clickInOrderSlots[number - 1] = invSlots.get(i);
                        }
                    }
                }
            }

            if (chestName.equals("Click in order!") && until == 100000) {
                for (int i = 10; i <= 25; i++) {
                    if (i != 17 && i != 18) {
                        Container container = mc.thePlayer.openContainer;
                        IInventory chestInventory = ((ContainerChest) container).getLowerChestInventory();
                        ItemStack click = chestInventory.getStackInSlot(i);
                        if (click != null) {
                            if (click.getItemDamage() != 14) break;
                            if ((chestInventory.getStackInSlot(i)).stackSize == 1 && terminalNumberNeeded[0] == 0) {
                                terminalNumberNeeded[0] = i;
                            }
                            if (terminalNumberNeeded[0] != 0 || terminalNumberNeeded[1] != 0) {
                                for (int j = 1; j < terminalNumberNeeded.length; j++) {
                                    ItemStack prevClick = chestInventory.getStackInSlot(terminalNumberNeeded[j - 1]);
                                    if (prevClick != null) {
                                        if (prevClick.getItemDamage() != 14)
                                            break;
                                        if (terminalNumberNeeded[j] == 0 && click.stackSize - (chestInventory.getStackInSlot(terminalNumberNeeded[j - 1])).stackSize == 1) {
                                            terminalNumberNeeded[j] = i;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (ToggleCommand.superpairsToggled && chestName.startsWith("Superpairs (")) {
                for (int i = 0; i < 53; i++) {
                    ItemStack itemStack = invSlots.get(i).getStack();
                    if (itemStack == null) continue;
                    String itemName = itemStack.getDisplayName();
                    if (Item.getIdFromItem(itemStack.getItem()) == 95 || Item.getIdFromItem(itemStack.getItem()) == 160) continue;
                    if (itemName.contains("Instant Find") || itemName.contains("Gained +")) continue;
                    if (itemName.contains("Enchanted Book")) {
                        itemName = itemStack.getTooltip(mc.thePlayer, false).get(3);
                    }
                    if (itemStack.stackSize > 1) {
                        itemName = itemStack.stackSize + " " + itemName;
                    }
                    if (experimentTableSlots[i] != null) continue;
                    experimentTableSlots[i] = itemStack.copy().setStackDisplayName(itemName);
                }
            }
        }

        if (titleTimer >= 0) {
            if (titleTimer == 0) {
                showTitle = false;
            }
            titleTimer--;
        }
        if (skillTimer >= 0) {
            if (skillTimer == 0) {
                showSkill = false;
            }
            skillTimer--;
        }
    }

    // Delay GUI by 1 tick
    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event) {
        if (guiToOpen != null) {
            Minecraft mc = Minecraft.getMinecraft();
            if (guiToOpen.startsWith("dankergui")) {
                int page = Character.getNumericValue(guiToOpen.charAt(guiToOpen.length() - 1));
                mc.displayGuiScreen(new DankerGui(page, ""));
            } else {
                switch (guiToOpen) {
                    case "displaygui":
                        mc.displayGuiScreen(new DisplayGui());
                        break;
                    case "onlyslayergui":
                        mc.displayGuiScreen(new OnlySlayerGui());
                        break;
                    case "editlocations":
                        mc.displayGuiScreen(new EditLocationsGui());
                        break;
                    case "puzzlesolvers":
                        mc.displayGuiScreen(new PuzzleSolversGui(1));
                        break;
                    case "experimentsolvers":
                        mc.displayGuiScreen(new ExperimentsGui());
                        break;
                    case "skilltracker":
                        mc.displayGuiScreen(new SkillTrackerGui());
                        break;
                    case "custommusic":
                        mc.displayGuiScreen(new CustomMusicGui());
                        break;
                }
            }
            guiToOpen = null;
        }
    }

    @SubscribeEvent
    public void onKey(KeyInputEvent event) {
        if (!Utils.inSkyblock) return;

        EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
        Minecraft mc = Minecraft.getMinecraft();
        World world = mc.theWorld;

        if (keyBindings[0].isPressed()) {
            player.sendChatMessage(lastMaddoxCommand);
        }
        if (keyBindings[1].isPressed()) {
            if (Utils.inDungeons) {
                player.dropOneItem(true);
            }
        }
        if (keyBindings[2].isPressed()) {
            if (skillStopwatch.isStarted() && skillStopwatch.isSuspended()) {
                skillStopwatch.resume();
                player.addChatMessage(new ChatComponentText(MAIN_COLOUR + "Skill tracker started."));
            } else if (!skillStopwatch.isStarted()) {
                skillStopwatch.start();
                player.addChatMessage(new ChatComponentText(MAIN_COLOUR + "Skill tracker started."));
            } else if (skillStopwatch.isStarted() && !skillStopwatch.isSuspended()) {
                skillStopwatch.suspend();
                player.addChatMessage(new ChatComponentText(MAIN_COLOUR + "Skill tracker paused."));
            }
        }
        if (keyBindings[3].isPressed()) {
            int[] order = new int[9];
            for (int i = 0; i <= 8; i++) {
                ItemStack item = player.inventory.getStackInSlot(i);
                if (item != null && item.getDisplayName().contains("Bonemerang"))
                    order[i] = 1;
                if ((item != null && item.getDisplayName().contains("Giant's Sword")) || (item != null && item.getDisplayName().contains("Emerald")))
                    this.sword = i;
                if (item != null && item.getDisplayName().contains("Bow"))
                    this.bow = i;
            }
            new Thread(() -> {
                for (int i = 0; i <= 8; i++) {
                    if (order[i] != 0) {
                        player.inventory.currentItem = i;
                        mc.playerController.sendUseItem(mc.thePlayer, world, player.inventory.getStackInSlot(i));
                        try {
                           Thread.sleep(DelayCommand.boneDelay);
                        } catch (InterruptedException e) {
                           e.printStackTrace();
                        }
                    }
                }
                if (this.sword != 10 && this.bow != 10 && SwapCommand.swapDelay != 0) {
                  player.inventory.currentItem = this.sword;
                  try {
                    Thread.sleep(SwapCommand.swapDelay);
                  } catch (InterruptedException e) {
                    e.printStackTrace();
                  }
                  player.inventory.currentItem = this.bow;
                }
            }).start();
        }
        if (keyBindings[4].isPressed()) {
          mazeId = 0;
          slotIn = -1;
        }
        if (keyBindings[5].isPressed()) {
            for (int i = 0; i <= SimonCommand.simonAmount; i++) {
                try {
                    Method method = mc.getClass().getDeclaredMethod("func_147121_ag", new Class[0]);
                    method.setAccessible(true);
                    method.invoke(mc, new Object[0]);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    @SubscribeEvent
    public void onGuiMouseInputPre(GuiScreenEvent.MouseInputEvent.Pre event) {
        if (!Utils.inSkyblock) return;
        if (Mouse.getEventButton() != 0 && Mouse.getEventButton() != 1 && Mouse.getEventButton() != 2)
            return; // Left click, middle click or right click
        if (!Mouse.getEventButtonState()) return;

        if (event.gui instanceof GuiChest) {
            Container containerChest = ((GuiChest) event.gui).inventorySlots;
            if (containerChest instanceof ContainerChest) {
                // a lot of declarations here, if you get scarred, my bad
                GuiChest chest = (GuiChest) event.gui;
                IInventory inventory = ((ContainerChest) containerChest).getLowerChestInventory();
                Slot slot = chest.getSlotUnderMouse();
                if (slot == null) return;
                ItemStack item = slot.getStack();
                String inventoryName = inventory.getDisplayName().getUnformattedText();
                if (item == null) {
                    if (MinecraftForge.EVENT_BUS.post(new ChestSlotClickedEvent(chest, inventory, inventoryName, slot))) event.setCanceled(true);
                } else {
                    if (MinecraftForge.EVENT_BUS.post(new ChestSlotClickedEvent(chest, inventory, inventoryName, slot, item))) event.setCanceled(true);
                }
            }
        } else {
            if (pickBlockBindSwapped) {
                gameSettings.keyBindPickBlock.setKeyCode(pickBlockBind);
                pickBlockBindSwapped = false;
            }
        }

        if (ToggleCommand.autoSkillTrackerToggled) {
			if (skillStopwatch.isStarted() && !skillStopwatch.isSuspended()) {
				skillStopwatch.suspend();
			}
		}

        clickInOrderSlots = new Slot[36];
        lastChronomatronRound = 0;
        chronomatronPattern.clear();
        chronomatronMouseClicks = 0;
        experimentTableSlots = new ItemStack[54];
        terminalColorNeeded = null;
    }

    @SubscribeEvent
    public void onGuiRender(GuiScreenEvent.BackgroundDrawnEvent event) {
        if (!Utils.inSkyblock) return;
        if (event.gui instanceof GuiChest) {
            GuiChest inventory = (GuiChest) event.gui;
            Container containerChest = inventory.inventorySlots;
            if (containerChest instanceof ContainerChest) {
                Minecraft mc = Minecraft.getMinecraft();
                ScaledResolution sr = new ScaledResolution(mc);
                int guiLeft = (sr.getScaledWidth() - 176) / 2;
                int guiTop = (sr.getScaledHeight() - 222) / 2;

                List<Slot> invSlots = inventory.inventorySlots.inventorySlots;
                String displayName = ((ContainerChest) containerChest).getLowerChestInventory().getDisplayName().getUnformattedText().trim();
                int chestSize = inventory.inventorySlots.inventorySlots.size();

                MinecraftForge.EVENT_BUS.post(new GuiChestBackgroundDrawnEvent(inventory, displayName, chestSize, invSlots));
            }
        }
    }

    @SubscribeEvent
    public void onServerConnect(ClientConnectedToServerEvent event) {
        event.manager.channel().pipeline().addBefore("packet_handler", "danker_packet_handler", new PacketHandler());
        System.out.println("Added packet handler to channel pipeline.");
    }

    public void increaseSeaCreatures() {
        if (LootCommand.empSCs != -1) {
            LootCommand.empSCs++;
        }
        if (LootCommand.empSCsSession != -1) {
            LootCommand.empSCsSession++;
        }
        // Only increment Yetis when in Jerry's Workshop
        List<String> scoreboard = ScoreboardHandler.getSidebarLines();
        for (String s : scoreboard) {
            String sCleaned = ScoreboardHandler.cleanSB(s);
            if (sCleaned.contains("Jerry's Workshop") || sCleaned.contains("Jerry Pond")) {
                if (LootCommand.yetiSCs != -1) {
                    LootCommand.yetiSCs++;
                }
                if (LootCommand.yetiSCsSession != -1) {
                    LootCommand.yetiSCsSession++;
                }
            }
        }

        LootCommand.seaCreatures++;
        LootCommand.fishingMilestone++;
        LootCommand.seaCreaturesSession++;
        LootCommand.fishingMilestoneSession++;
        ConfigHandler.writeIntConfig("fishing", "seaCreature", LootCommand.seaCreatures);
        ConfigHandler.writeIntConfig("fishing", "milestone", LootCommand.fishingMilestone);
        ConfigHandler.writeIntConfig("fishing", "empSC", LootCommand.empSCs);
        ConfigHandler.writeIntConfig("fishing", "yetiSC", LootCommand.yetiSCs);

    }

}
