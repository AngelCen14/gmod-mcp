if SERVER then
    include("autorun/server/admin_command.lua")
    include("autorun/server/sv_utils.lua")

    addAdminCommand("killplayers", function(ply, cmd, args)
        if #args == 0 then
            ply:PrintMessage(HUD_PRINTCONSOLE, "Usage: killplayers <player_name1> <player_name2> ...")
            return
        end

        for _, targetName in ipairs(args) do
            local targetPlayer = getPlayerByNick(targetName)
            if IsValid(targetPlayer) then
            targetPlayer:Kill()
                print("Player " .. targetPlayer:Nick() .. " has been killed.")
            else
                print("Player '" .. targetName .. "' not found.")
            end
        end
    end)

    addAdminCommand("spawnnpc", function(ply, cmd, args)
        if #args < 4 then
            ply:PrintMessage(HUD_PRINTCONSOLE, "Usage: spawnnpc <npc_class> <x> <y> <z>")
            return
        end

        local npcClass = args[1]
        local npc = ents.Create(npcClass)

        npc:SetPos(Vector(args[2], args[3], args[4]))
        npc:Spawn()
    end)
end