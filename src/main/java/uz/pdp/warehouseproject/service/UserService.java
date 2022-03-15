package uz.pdp.warehouseproject.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.warehouseproject.dto.receive.UserReceiveDto;
import uz.pdp.warehouseproject.entity.User;
import uz.pdp.warehouseproject.entity.Warehouse;
import uz.pdp.warehouseproject.repository.UserRepository;
import uz.pdp.warehouseproject.response.ApiResponse;
import uz.pdp.warehouseproject.service.base.BaseService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static uz.pdp.warehouseproject.consts.BaseConstants.EDIT;
import static uz.pdp.warehouseproject.consts.BaseConstants.SAVED;
import static uz.pdp.warehouseproject.response.BaseResponse.DELETED;
import static uz.pdp.warehouseproject.response.BaseResponse.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserService implements BaseService<UserReceiveDto, User> {

    private final UserRepository userRepository;
    private final WarehouseService warehouseService;
    private final ModelMapper modelMapper;

    @Override
    public ApiResponse add(UserReceiveDto item) {
        boolean exists = userRepository.existsByPhoneNumber(item.getPhoneNumber());
        if(exists)
            return new ApiResponse(2, "User already exists", item);
        Set<Warehouse> warehouseSet = new HashSet<>();
        for (Integer warehouseId : item.getWarehouses()) {
            Warehouse byId = warehouseService.checkById(warehouseId);
            if(byId != null)
                warehouseSet.add(byId);
        }

        User mappedUser = modelMapper.map(item, User.class);
        mappedUser.setWarehouses(warehouseSet);

        return new ApiResponse(1, SAVED,  userRepository.save(mappedUser));
    }

    @Override
    public ApiResponse edit(Integer id, UserReceiveDto item) {
        User user = checkById(id);
        if(user == null)
            return NOT_FOUND;

        String phoneNumber = item.getPhoneNumber();
        if(userRepository.existsByPhoneNumber(phoneNumber))
            return new ApiResponse(2, "User with this phoneNumber already exists", item);

        User mappedUser = modelMapper.map(item, User.class);
        return new ApiResponse(1, EDIT,  userRepository.save(mappedUser));
    }

    @Override
    public ApiResponse delete(Integer id) {
        User user = checkById(id);
        if(user == null)
            return NOT_FOUND;

        user.setActive(false);
        userRepository.save(user);

        return DELETED;
    }

    @Override
    public ApiResponse getAll() {
        List<User> all = userRepository.findAll();
        return new ApiResponse(1, "All users", all);
    }

    @Override
    public ApiResponse getById(Integer id) {
        User user = checkById(id);
        if(user == null)
            return NOT_FOUND;

        return new ApiResponse(1, "User", user);
    }

    @Override
    public User checkById(Integer id) {
        Optional<User> optional = userRepository.findById(id);
        if(optional.isPresent() && optional.get().isActive())
            return optional.get();

        return null;
    }
}
