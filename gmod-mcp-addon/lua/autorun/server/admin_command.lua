if SERVER then
    // local functions
    local function hasPermission(ply)
        // server console allowed
        if not IsValid(ply) then return true end

        // admins allowed
        if ply:IsAdmin() then return true end

        ply:PrintMessage(HUD_PRINTCONSOLE, "You don't have permission to use this command.")
        return false
    end

    // global functions
    function addAdminCommand(cmdName, callback)
        concommand.Add(cmdName, function(ply, cmd, args)
            if not hasPermission(ply) then return end
            callback(ply, cmd, args)
        end)
    end
end